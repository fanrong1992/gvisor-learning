# Add a System Call To gVisor

### 添加 syscall 的主要步骤

参考[我在gvisor论坛上面的Topic](https://groups.google.com/d/msg/gvisor-users/-_IgtpPHiic/LoqmMtAuBQAJ)，在gVisor中添加一个系统调用主要分三步

- 在`pkg/sentry/syscalls/linux/linux64.go`文件当中的`var AMD64`变量当中添加调用函数名和系统调用号
- 在`pkg/sentry/syscalls/linux`目录下添加`×××.go`文件，在这个文件里定义调用函数
- 在`pkg/sentry/syscalls/linux/BUILD`文件当中添加构建项

以上三步完成之后，重新编译gvisor就可以在Application当中进行系统调用(按照系统调用号)，调用我们刚刚定义的函数。

### gVisor 关于 redirect syscall 的原理

[Anatomy of a system call, part 1](https://lwn.net/Articles/604287/) 介绍了Linux实现系统调用的具体流程。

和Linux实现系统调用类似，gVisor 也存在着这样的机制。

gVisor 在 sentry 当中实现系统调用，具体的架构如下(来自于[申文博老师的runc, gvisor, and kata container](https://wenboshen.org/posts/2018-12-01-sectainer.html))

```
          Application     --> Guest Ring 3
         ----------------
          Sentry (ring0)  --> Guest Ring 0
GUEST
-------------------------------------------
HOST
          Sentry (kvm)    --> Host Ring 3
         -------------
          Host kernel     --> Host Ring 0
```

**Sentry** 在初始化的时候，将系统调用函数放入 **MSR_LSTAR** 寄存器当中，当 **Application** 发生系统调用的时候，机器会直接陷入 trap，然后自动调用存放在 **MSR_LSTAR** 寄存器当中的函数，即`sysenter()`。

每次 **Application** 发生系统调用或者是 **sentry** 自己进行系统调用的时候，这个 `sysenter()` 函数都会执行。这个函数是用汇编代码来写的，主要做的事情就是在 **context switch** 的过程当中保存寄存器的值，然后根据 **kernel space** 里面的 **syscall table** 来进行跳转到具体的系统调用实现函数中去。

**sentry** 当中的 **kernel space** 其实在 **Host** 看来是 **user space** 当中分配给 **sentry** 的其中一部分。

当添加一个系统调用的时候，需要做的就是编写调用函数，同时将系统调用函数名和调用号都添加到 **syscall table** 当中去。

### 添加调用函数名和调用号

在`pkg/sentry/syscalls/linux/linux64.go`文件当中的`var AMD64`变量当中添加调用函数名和系统调用号。

上文讲到的 **syscall table** 定义在 `var AMD64` 变量当中的 `Table` 属性下。

```go
var AMD64 = &kernel.SyscallTable{
    OS:   abi.Linux,
    Arch: arch.AMD64,
    Version: kernel.Version{
        Sysname: "Linux",
        Release: "4.4",
        Version: "#1 SMP Sun Jan 10 15:06:54 PST 2016",
    },
    AuditNumber: _AUDIT_ARCH_X86_64,
    Table: map[uintptr]kernel.SyscallFn{
        0:  Read,
        1:  Write,
        2:  Open,
        3:  Close,
        4:  Stat,
        // ...
        400: Square,
    }
    // ...
}
```

添加新的系统调用的时候，形式应该和上述的调用一样，以`调用号: 调用函数名`的形式，如上述代码中的`400: Square`。

这一步相当于在调用表当中注册了新的调用函数。接下来就是定义这个新添加的调用函数。

### 定义调用函数

定义调用函数的目录是确定的，在 `pkg/sentry/syscalls/linux`。

在该目录新建一个`.go`文件，这里以之前添加的 `Square` 调用函数为例，在目录下新建一个名为`sys_square.go`的文件

```go
package linux

import (
    "gvisor.googlesource.com/gvisor/pkg/sentry/arch"
    "gvisor.googlesource.com/gvisor/pkg/sentry/kernel"
)

// Square returns the square of arg[0]
func Square(t *kernel.Task, args arch.SyscallArguments) (uintptr, *kernel.SyscallControl, error) {
    num := args[0].Int()
    num = num * num
    return uintptr(num), nil, nil
}
```

这里的`Square()`函数接受一个参数并返回它的平方。

注意这里的 **package** 和 **import** 部分必须一致。函数名要和之前注册的相对应。

### 添加构建项

打开`pkg/sentry/syscalls/linux/BUILD`文件，添加新建的`sys_square.go`文件。

```
package(licenses = ["notice"])
load("//tools/go_stateify:defs.bzl", "go_library")
go_library(
    name = "linux",
    srcs = [
        "error.go",
        "flags.go",
        "linux64.go",
        "timespec.go",
        ...
        "sys_square.go"     <-------------- new
    ],
...
```

重新 **build** gVisor。

### 测试

参考[这篇文章](https://www.fengbohello.top/archives/go-env-docker)，使用 **docker** 来构建 **go** 语言环境，生成可执行文件。

```go
// test-square.go
package main
import (
    "fmt"
    "os"
    "syscall"
)

const SQUARE = 400 
func main() {
    r1, _, errNo := syscall.RawSyscall(SQUARE, 5, 0, 0)
    if errNo != 0 { 
        fmt.Fprintf(os.Stderr, "ERROR: %d\n", errNo)
        os.Exit(1)
    }
    fmt.Printf("%d\n", r1) 
}      
```

这个文件通过`syscall.RawSyscall(SQUARE, 5, 0, 0)`利用系统调用号来进行系统调用，这里用到的参数仅仅为第一个`5`。

将该程序编译之后得到可执行文件`test-square`，在 **gVisor** 当中运行它，得到如下输出

```shell
root@91071194a4c4:/code# ls
test-square  test-square.go
root@91071194a4c4:/code# ./test-square 
25
```

输出为`5`的平方`25`。

