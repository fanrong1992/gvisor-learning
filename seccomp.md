# 关于 Seccomp 的研究

#### seccomp in Linux

参考[seccomp基本功能](https://github.com/hujikoy/hello-seccomp)一文，以及相关的参考资料，大致明确 **seccomp** 的具体细节。



#### seccomp in gVisor

从日志文件 `xxx.boot` 文件的输出入手，定位`Installing seccomp filters for 55 syscalls`这句话，找到了`pkg/seccomp/seccomp.go`文件，现在研究一下上下文的背景。

几个关键的函数和结构体需要解读，目前来看，有`SetFilter()`函数，`Install()`函数，还有`rules`结构体。