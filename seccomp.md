# 关于 Seccomp 的研究

从日志文件 `xxx.boot` 文件的输出入手，定位`Installing seccomp filters for 55 syscalls`这句话，找到了`pkg/seccomp/seccomp.go`文件，现在研究一下上下文的背景。

