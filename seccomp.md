# 关于 Seccomp 的研究

#### seccomp in Linux

参考[seccomp基本功能](https://github.com/hujikoy/hello-seccomp)一文，以及相关的参考资料，大致明确 **seccomp** 的具体细节。



#### seccomp in gVisor

从日志文件 `xxx.boot` 文件的输出入手，定位`Installing seccomp filters for 55 syscalls`这句话，找到了`pkg/seccomp/seccomp.go`文件，现在研究一下上下文的背景。

几个关键的函数和结构体需要解读，目前来看，有`SetFilter()`函数，`Install()`函数，还有`rules`结构体。



×× 疑问：如果guest application进行的系统调用sentry没有，sentry会做什么操作？



在`Install()`函数当中进行 print Stack Trace 操作，观察调用关系

```go
goroutine 1 [running]:
runtime/debug.Stack(0xd24c06, 0x36, 0xc000350220)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/pkg/seccomp.Install(0xc0001721b0, 0xc0001c12f0, 0xffffffffffffffff)
	pkg/seccomp/seccomp.go:67 +0x122
gvisor.googlesource.com/gvisor/runsc/boot/filter.Install(0xe40e80, 0xc00000e9d0, 0xc0001c0000, 0x5, 0xc000083b01, 0xc000083aa0)
	runsc/boot/filter/filter.go:65 +0x643
gvisor.googlesource.com/gvisor/runsc/boot.(*Loader).run(0xc00017a8c0, 0x0, 0x0)
	runsc/boot/loader.go:486 +0x7fd
gvisor.googlesource.com/gvisor/runsc/boot.(*Loader).Run(0xc00017a8c0, 0x0, 0x0)
	runsc/boot/loader.go:442 +0x2f
gvisor.googlesource.com/gvisor/runsc/cmd.(*Boot).Execute(0xc0001741b0, 0xe384e0, 0xc000050008, 0xc0001805a0, 0xc000156a60, 0x2, 0x2, 0x0)
	runsc/cmd/boot.go:247 +0xcb0
github.com/google/subcommands.(*Commander).Execute(0xc000092000, 0xe384e0, 0xc000050008, 0xc000156a60, 0x2, 0x2, 0xc000034000)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:251 +0x1452
```

有 filter.install() -> seccomp.install() 这样一个过程。而这些都是在执行 Execute 操作的一开始进行的。

现在来看这个函数具体做了什么。

```go
// Install generates BPF code based on the set of syscalls provided. It only
// allows syscalls that conform to the specification. Syscalls that violate the
// specification will trigger RET_KILL_PROCESS, except for the cases below.
// ...
func Install(rules SyscallRules) error {
```

这里有一个`BRF code`的概念，我目前并不清楚，但函数大致的作用可以通过阅读函数体得知，目前我的猜测是根据给出的条件来决定拦截的白名单。

```go
func Install(rules SyscallRules) error {
	defaultAction, err := defaultAction()
	if err != nil {
		return err
	}

	// Uncomment to get stack trace when there is a violation.
	// defaultAction = linux.BPFAction(linux.SECCOMP_RET_TRAP)
```

先看第一部分，这里调用了 `defaultAction()` 函数，这个函数同样定义在这个文件当中，如下所示

```go
func defaultAction() (linux.BPFAction, error) {
	available, err := isKillProcessAvailable()
	if err != nil {
		return 0, err
	}
	if available {
		return linux.SECCOMP_RET_KILL_PROCESS, nil
	}
	return linux.SECCOMP_RET_TRAP, nil
}
```

它对系统本身做了一个检查，根据能否终止进程来返回不同的字段，这些自动将用于拦截系统调用的过程当中，在注释当中也有提到 `RET_KILL_PROCESS`，从上面的函数可以看出，只有在可以终止进程的这个权限成立的时候，`available` 才会使用这个值，否则就是其他的值

```go
// RET_TRAP is used in violations, instead of RET_KILL_PROCESS, in the
// following cases:
//	 1. Kernel doesn't support RET_KILL_PROCESS: RET_KILL_THREAD only kills the
//      offending thread and often keeps the sentry hanging.
//   2. Debug: RET_TRAP generates a panic followed by a stack trace which is
//      much easier to debug then RET_KILL_PROCESS which can't be caught.
//
// Be aware that RET_TRAP sends SIGSYS to the process and it may be ignored,
// making it possible for the process to continue running after a violation.
// However, it will leave a SECCOMP audit event trail behind. In any case, the
// syscall is still blocked from executing.
```

如果想要在拦截 syscall 的同时打印 stack trace，那就可以直接将 `defaultAction = linux.BPFAction(linux.SECCOMP_RET_TRAP)` 取消注释。

不同的模式下，在 log 当中打印出来的值是不同的，默认情况下是 `kill process`，第二种情况则是 `trap (0)`。

那么再回到这个输出语句

```
log.Infof("Installing seccomp filters for %d syscalls (action=%v)", len(rules), defaultAction)
```

正常情况下的输出结果是这样的

```
Installing seccomp filters for 53 syscalls (action=kill process)
```

此时 rules 变量的长度是 53，defaultAction 变量的值是 `linux.SECCOMP_RET_KILL_PROCESS`。

从这个输出的语句可以知道，rules 当中的值，就是 seccomp filter 允许的系统调用，因此接下来先看看这个变量里面到底有那些 syscall。

