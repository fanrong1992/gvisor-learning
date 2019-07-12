# gVisor Learing

本文主要探讨了关于gvisor的各种运行机制，通过修改gVisor源码，加入stack trace的相关功能，探讨gVisor内部的函数调用。

### gVisor Setup

根据输出的日志来看，总共有已下几个日志文件，可以根据日志文件的创建时间来猜测这些进程的开始顺序。

```
xxx.create
xxx.gofer
xxx.boot
xxx.state
xxx.start
xxx.state
xxx.state
xxx.delete
```

总共8个日志文件，其中create文件是最开始的文件，因此可以从`.create`文件入手，来研究`gVisor`相关函数的调用顺序。

##### `.create`文件的内容

运行命令`docker run --runtime=runsc-kvm hello-world`得到的`.create`日志文件如下所示。

```
I0613 14:29:18.178927   21669 x:0] ***************************
I0613 14:29:18.179084   21669 x:0] Args: [/usr/local/bin/runsc --platform=kvm --debug-log=/tmp/runsc/ --debug --strace --root /var/run/docker/runtime-runsc-kvm/moby --log /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format json create --bundle /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --pid-file /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/init.pid 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222]
I0613 14:29:18.179179   21669 x:0] Version release-20190529.1-39-ge03aefd88c42-dirty
I0613 14:29:18.179208   21669 x:0] PID: 21669
I0613 14:29:18.179245   21669 x:0] UID: 0, GID: 0
I0613 14:29:18.179270   21669 x:0] Configuration:
I0613 14:29:18.179293   21669 x:0] 		RootDir: /var/run/docker/runtime-runsc-kvm/moby
I0613 14:29:18.179318   21669 x:0] 		Platform: kvm
I0613 14:29:18.179349   21669 x:0] 		FileAccess: exclusive, overlay: false
I0613 14:29:18.179379   21669 x:0] 		Network: sandbox, logging: false
I0613 14:29:18.179408   21669 x:0] 		Strace: true, max size: 1024, syscalls: []
I0613 14:29:18.179441   21669 x:0] ***************************
W0613 14:29:18.183597   21669 x:0] AppArmor profile "docker-default" is being ignored
W0613 14:29:18.183656   21669 x:0] Seccomp spec is being ignored
D0613 14:29:18.183713   21669 x:0] Spec: &{Version:1.0.1-dev Process:0xc00017cf70 Root:0xc0001acfe0 Hostname:8496793d4d0a Mounts:[{Destination:/proc Type:proc Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/proc Options:[nosuid noexec nodev]} {Destination:/dev Type:tmpfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/tmpfs Options:[nosuid strictatime mode=755 size=65536k]} {Destination:/dev/pts Type:devpts Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/devpts Options:[nosuid noexec newinstance ptmxmode=0666 mode=0620 gid=5]} {Destination:/sys Type:sysfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/sysfs Options:[nosuid noexec nodev ro]} {Destination:/sys/fs/cgroup Type:cgroup Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/cgroup Options:[ro nosuid noexec nodev]} {Destination:/dev/mqueue Type:mqueue Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mqueue Options:[nosuid noexec nodev]} {Destination:/etc/resolv.conf Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/resolv.conf Options:[rbind rprivate]} {Destination:/etc/hostname Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hostname Options:[rbind rprivate]} {Destination:/etc/hosts Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hosts Options:[rbind rprivate]} {Destination:/dev/shm Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mounts/shm Options:[rbind rprivate]}] Hooks:0xc00015a0f0 Annotations:map[] Linux:0xc0001782a0 Solaris:<nil> Windows:<nil>}
D0613 14:29:18.183960   21669 x:0] Spec.Hooks: &{Prestart:[{Path:/proc/1466/exe Args:[libnetwork-setkey 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 e4fe84bac6dbdce3579344f7e93a7401bac629c608c19ed4d5dd939dbd287de0] Env:[] Timeout:<nil>}] Poststart:[] Poststop:[]}
D0613 14:29:18.184017   21669 x:0] Spec.Linux: &{UIDMappings:[] GIDMappings:[] Sysctl:map[] Resources:0xc0001648a0 CgroupsPath:/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 Namespaces:[{Type:mount Path:} {Type:network Path:} {Type:uts Path:} {Type:pid Path:} {Type:ipc Path:}] Devices:[] Seccomp:0xc0000719c0 RootfsPropagation: MaskedPaths:[/proc/asound /proc/acpi /proc/kcore /proc/keys /proc/latency_stats /proc/timer_list /proc/timer_stats /proc/sched_debug /proc/scsi /sys/firmware] ReadonlyPaths:[/proc/bus /proc/fs /proc/irq /proc/sys /proc/sysrq-trigger] MountLabel: IntelRdt:<nil>}
D0613 14:29:18.184125   21669 x:0] Spec.Linux.Resources.Memory: &{Limit:<nil> Reservation:<nil> Swap:<nil> Kernel:<nil> KernelTCP:<nil> Swappiness:<nil> DisableOOMKiller:0xc0001b0e66}
D0613 14:29:18.184167   21669 x:0] Spec.Linux.Resources.CPU: &{Shares:0xc0001b0e68 Quota:<nil> Period:<nil> RealtimeRuntime:<nil> RealtimePeriod:<nil> Cpus: Mems:}
D0613 14:29:18.184209   21669 x:0] Spec.Linux.Resources.BlockIO: &{Weight:0xc0001b0e78 LeafWeight:<nil> WeightDevice:[] ThrottleReadBpsDevice:[] ThrottleWriteBpsDevice:[] ThrottleReadIOPSDevice:[] ThrottleWriteIOPSDevice:[]}
D0613 14:29:18.184260   21669 x:0] Spec.Linux.Resources.Network: <nil>
D0613 14:29:18.184294   21669 x:0] Spec.Process: &{Terminal:false ConsoleSize:<nil> User:{UID:0 GID:0 AdditionalGids:[] Username:} Args:[/hello] Env:[PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin HOSTNAME=8496793d4d0a] Cwd:/ Capabilities:0xc000148280 Rlimits:[] NoNewPrivileges:false ApparmorProfile:docker-default OOMScoreAdj:0xc0001b0ba0 SelinuxLabel:}
D0613 14:29:18.184366   21669 x:0] Spec.Root: &{Path:/var/lib/docker/overlay2/aa0bfb5ad159f36c0a3d28e0e569ef1f8c5a3980dcc5ffc7fa8f8c1399147a15/merged Readonly:false}
D0613 14:29:18.184412   21669 x:0] Spec.Mounts: [{Destination:/proc Type:proc Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/proc Options:[nosuid noexec nodev]} {Destination:/dev Type:tmpfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/tmpfs Options:[nosuid strictatime mode=755 size=65536k]} {Destination:/dev/pts Type:devpts Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/devpts Options:[nosuid noexec newinstance ptmxmode=0666 mode=0620 gid=5]} {Destination:/sys Type:sysfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/sysfs Options:[nosuid noexec nodev ro]} {Destination:/sys/fs/cgroup Type:cgroup Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/cgroup Options:[ro nosuid noexec nodev]} {Destination:/dev/mqueue Type:mqueue Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mqueue Options:[nosuid noexec nodev]} {Destination:/etc/resolv.conf Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/resolv.conf Options:[rbind rprivate]} {Destination:/etc/hostname Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hostname Options:[rbind rprivate]} {Destination:/etc/hosts Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hosts Options:[rbind rprivate]} {Destination:/dev/shm Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mounts/shm Options:[rbind rprivate]}]
D0613 14:29:18.184566   21669 x:0] Create container "8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222" in root dir: /var/run/docker/runtime-runsc-kvm/moby
D0613 14:29:18.184798   21669 x:0] Creating new sandbox for container "8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.184868   21669 x:0] Creating cgroup "/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188319   21669 x:0] Joining cgroup "/sys/fs/cgroup/net_prio/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188482   21669 x:0] Joining cgroup "/sys/fs/cgroup/devices/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188601   21669 x:0] Joining cgroup "/sys/fs/cgroup/perf_event/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188764   21669 x:0] Joining cgroup "/sys/fs/cgroup/pids/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188879   21669 x:0] Joining cgroup "/sys/fs/cgroup/blkio/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.188984   21669 x:0] Joining cgroup "/sys/fs/cgroup/cpu/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189148   21669 x:0] Joining cgroup "/sys/fs/cgroup/memory/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189263   21669 x:0] Joining cgroup "/sys/fs/cgroup/net_cls/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189364   21669 x:0] Joining cgroup "/sys/fs/cgroup/systemd/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189485   21669 x:0] Joining cgroup "/sys/fs/cgroup/cpuset/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189604   21669 x:0] Joining cgroup "/sys/fs/cgroup/freezer/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
D0613 14:29:18.189965   21669 x:0] Starting gofer: /proc/self/exe [--root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 gofer --bundle /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --spec-fd=5 --mounts-fd=6 --io-fds=7 --io-fds=8 --io-fds=9 --io-fds=10]
I0613 14:29:18.192156   21669 x:0] Gofer started, PID: 21674
I0613 14:29:18.192512   21669 x:0] Creating sandbox process with addr: runsc-sandbox.8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222
I0613 14:29:18.192651   21669 x:0] Sandbox will be started in new mount, IPC and UTS namespaces
I0613 14:29:18.192687   21669 x:0] Sandbox will be started in a new PID namespace
I0613 14:29:18.192715   21669 x:0] Sandbox will be started in the container's network namespace: {Type:network Path:}
I0613 14:29:18.192917   21669 x:0] Sandbox will be started in new user namespace
D0613 14:29:18.193330   21669 x:0] Donating FD 3: "/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json"
D0613 14:29:18.193394   21669 x:0] Donating FD 4: "/tmp/runsc/runsc.log.20190613-142918.192358.boot"
D0613 14:29:18.193427   21669 x:0] Donating FD 5: "control_server_socket"
D0613 14:29:18.193461   21669 x:0] Donating FD 6: "|0"
D0613 14:29:18.193487   21669 x:0] Donating FD 7: "/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/config.json"
D0613 14:29:18.193526   21669 x:0] Donating FD 8: "|1"
D0613 14:29:18.193550   21669 x:0] Donating FD 9: "sandbox IO FD"
D0613 14:29:18.193586   21669 x:0] Donating FD 10: "sandbox IO FD"
D0613 14:29:18.193613   21669 x:0] Donating FD 11: "sandbox IO FD"
D0613 14:29:18.193639   21669 x:0] Donating FD 12: "sandbox IO FD"
D0613 14:29:18.193665   21669 x:0] Donating FD 13: "/dev/kvm"
D0613 14:29:18.193691   21669 x:0] Donating FD 14: "/dev/stdin"
D0613 14:29:18.193717   21669 x:0] Donating FD 15: "/dev/stdout"
D0613 14:29:18.193743   21669 x:0] Donating FD 16: "/dev/stderr"
D0613 14:29:18.193775   21669 x:0] Starting sandbox: /proc/self/exe [runsc-sandbox --root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 boot --bundle=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --controller-fd=5 --mounts-fd=6 --spec-fd=7 --start-sync-fd=8 --io-fds=9 --io-fds=10 --io-fds=11 --io-fds=12 --device-fd=13 --stdio-fds=14 --stdio-fds=15 --stdio-fds=16 --pidns=true --setup-root --cpu-num 4 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222]
D0613 14:29:18.193870   21669 x:0] SysProcAttr: &{Chroot: Credential:0xc0001f28d0 Ptrace:false Setsid:true Setpgid:false Setctty:false Noctty:false Ctty:0 Foreground:false Pgid:0 Pdeathsig:signal 0 Cloneflags:0 Unshareflags:0 UidMappings:[{ContainerID:0 HostID:65533 Size:1} {ContainerID:65534 HostID:65534 Size:1}] GidMappings:[{ContainerID:65534 HostID:65534 Size:1}] GidMappingsEnableSetgroups:false AmbientCaps:[]}
I0613 14:29:18.202738   21669 x:0] Sandbox started, PID: 21683
D0613 14:29:18.244070   21669 x:0] Restoring cgroup "/sys/fs/cgroup/cpuset"
D0613 14:29:18.244121   21669 x:0] Save container "8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
I0613 14:29:18.244369   21669 x:0] Exiting with status: 0
```

通过查找源代码，可以发现第`1-12`行来自于`/gvisor/runsc/main.go`文件，有关代码如下所示

```go
log.Infof("***************************")
log.Infof("Args: %s", os.Args)
log.Infof("Version %s", version)
log.Infof("PID: %d", os.Getpid())
log.Infof("UID: %d, GID: %d", os.Getuid(), os.Getgid())
log.Infof("Configuration:")
log.Infof("\t\tRootDir: %s", conf.RootDir)
log.Infof("\t\tPlatform: %v", conf.Platform)
log.Infof("\t\tFileAccess: %v, overlay: %t", conf.FileAccess, conf.Overlay)
log.Infof("\t\tNetwork: %v, logging: %t", conf.Network, conf.LogPackets)
log.Infof("\t\tStrace: %t, max size: %d, syscalls: %s", conf.Strace, conf.StraceLogSize, conf.StraceSyscalls)
log.Infof("***************************")
```

这些代码打印了一些关于`runtime`的基本信息，

通过上面所示的`.create`日志文件，我们可以对照时间，来推断在打印`runtime`基本信息之后，就创建了`gofer`日志文件，同时进行gofer初始化。

本次运行产生的日志文件全名如下所示

```
runsc.log.20190613-142918.178685.create
runsc.log.20190613-142918.189752.gofer
runsc.log.20190613-142918.192358.boot
runsc.log.20190613-142918.257769.state
runsc.log.20190613-142918.262384.start
runsc.log.20190613-142918.490620.state
runsc.log.20190613-142918.607168.state
runsc.log.20190613-142918.611781.state
```

**对照时间我们发现，在`.create`文件当中，在`.gofer`文件创建的时间节点附近，的确有相关的语句输出，即第`39`行，`Starting gofer:...`。**

再查找`.boot`文件创建的时间，发现没有明显的输出语句是关于`boot`操作的，但是在第`62`行有`Sandbox started, PID:....`的语句，说明`boot`操作极有可能是在这段时间进行的，在创建`sandbox`并存储容器之后，`create`进程便退出了，因此关于`.create`文件的研究，主要集中在是如初始化`gofer`与`sandbox`，和在这些操作中间还做了什么。

关于`/gvisor/runsc/main.go`文件的具体内容和函数究竟做了什么，我的`Github Docker`目录下面已经有详细的说明了，在这里就不再赘述，这里探讨的主要是这些日志文件相关的初始化操作的顺序和具体实现。

##### 从`create`到`boot`

上一节讲到在`.create`文件的开始部分，先输出了一些初始化的基本信息，现在来看一下之后输出的部分。

从第`13`行开始，就进入到了比较难理解的部分。首先找到第13行的输出，对应源文件的位置是在`/runsc/specutils/specutils.go`文件当中。代码如下

```
	// Docker uses AppArmor by default, so just log that it's being ignored.
	if spec.Process.ApparmorProfile != "" {
		// add by zty in oder to watch stack trace
		// using "runtime/debug" PrintStack() -> Maybe use runtime.Stack() can collect the info and output to log
		debug.PrintStack()
		log.Warningf("AppArmor profile %q is being ignored", spec.Process.ApparmorProfile)
	}
```

我在其中加入了`PrintStack()`操作，将堆栈调用打印在控制台中如下所示

```
goroutine 1 [running]:
runtime/debug.Stack(0xc00018ab63, 0xc0000cf960, 0x0)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/specutils.ValidateSpec(0xc0000331f0, 0x4e82, 0x7e00)
	runsc/specutils/specutils.go:100 +0x5e6
gvisor.googlesource.com/gvisor/runsc/specutils.ReadSpecFromFile(0x7ffff6a8dd4c, 0x74, 0xc00000e0d0, 0x0, 0x0, 0x8)
	runsc/specutils/specutils.go:183 +0x59a
gvisor.googlesource.com/gvisor/runsc/specutils.ReadSpec(0x7ffff6a8dd4c, 0x74, 0x0, 0x0, 0x0)
	runsc/specutils/specutils.go:166 +0x258
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc000070940, 0xe384c0, 0xc00003a028, 0xc000166600, 0xc00013ca80, 0x2, 0x2, 0x7f51c3308008)
	runsc/cmd/create.go:90 +0xca
github.com/google/subcommands.(*Commander).Execute(0xc000032070, 0xe384c0, 0xc00003a028, 0xc00013ca80, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:245 +0x1452

```

可以看到，在`runsc/main.go`当中仍然是处于第`245`行

```go
subcmdCode := subcommands.Execute(context.Background(), conf, &ws)
```

然后调用了一系列外部的库函数，接着运行到`runsc/cmd/create.go`文件中，最后到了`runsc/spectils`文件里。

`subcommands`应该就是类似子命令运行的一个工具，这里主要关注的还是`runsc/cmd/create.go`与`runsc/specutils/specutils.go`这两个文件的作用。

之所以会运行`runsc/cmd/create.go`，是因为子命令当中包含了`create`参数，那么现在来研究一下这个命令究竟做了什么事情。

```go
// Execute implements subcommands.Command.Execute.
func (c *Create) Execute(_ context.Context, f *flag.FlagSet, args ...interface{}) subcommands.ExitStatus {
	if f.NArg() != 1 {
		f.Usage()
		return subcommands.ExitUsageError
	}

	id := f.Arg(0)
	conf := args[0].(*boot.Config)

	bundleDir := c.bundleDir
	if bundleDir == "" {
		bundleDir = getwdOrDie()
	}
	spec, err := specutils.ReadSpec(bundleDir)
	if err != nil {
		Fatalf("reading spec: %v", err)
	}
	specutils.LogSpec(spec)

	// Create the container. A new sandbox will be created for the
	// container unless the metadata specifies that it should be run in an
	// existing container.
	if _, err := container.Create(id, spec, conf, bundleDir, c.consoleSocket, c.pidFile, c.userLog); err != nil {
		Fatalf("creating container: %v", err)
	}
	return subcommands.ExitSuccess
}
```

之前的`runsc/cmd/create.go:90 +0xca` Stack Trace表明，当前`create.go`文件运行到了`spec, err := specutils.ReadSpec(bundleDir)`这一句。由此进入了`runsc/specutils/specutils.go`的`ReadSpec()`函数。在该函数后续调用了`ValidateSpec()`函数，该函数的说明是`ValidateSpec validates that the spec is compatible with runsc`，意思就是**验证spec是否与runsc兼容**。

日志文件当中关于`App Armor`和`Seccomp`的两句话(13-14)都是来源于此。

```go
	// Docker uses AppArmor by default, so just log that it's being ignored.
	if spec.Process.ApparmorProfile != "" {
		log.Warningf("AppArmor profile %q is being ignored", spec.Process.ApparmorProfile)
	}

	// TODO(b/72226747): Apply seccomp to application inside sandbox.
	if spec.Linux != nil && spec.Linux.Seccomp != nil {
		log.Warningf("Seccomp spec is being ignored")
	}
```

接下来的日志输出是来自于`runsc/specutils/specutils.go`的`LogSpecSpec()`函数

```go
// LogSpec logs the spec in a human-friendly way.
func LogSpec(spec *specs.Spec) {
	log.Debugf("Spec: %+v", spec)
	log.Debugf("Spec.Hooks: %+v", spec.Hooks)
	log.Debugf("Spec.Linux: %+v", spec.Linux)
	if spec.Linux != nil && spec.Linux.Resources != nil {
		res := spec.Linux.Resources
		log.Debugf("Spec.Linux.Resources.Memory: %+v", res.Memory)
		log.Debugf("Spec.Linux.Resources.CPU: %+v", res.CPU)
		log.Debugf("Spec.Linux.Resources.BlockIO: %+v", res.BlockIO)
		log.Debugf("Spec.Linux.Resources.Network: %+v", res.Network)
	}
	log.Debugf("Spec.Process: %+v", spec.Process)de
	log.Debugf("Spec.Root: %+v", spec.Root)
	log.Debugf("Spec.Mounts: %+v", spec.Mounts)
}
```

该函数的调用来自于`runsc/cmd/create.go`，在`Execute()`函数中调用了`specutils.LogSpec(spec)`。

这里主要就是在日志文件当中打印`Spec`的相关参数设置，方便后续的调试。

注意这里`spec`是一个很重要的概念，应该和runtime的很多设置相关，包括之前的`App Armor`还有`Seccomp`。后续可以深入研究一下`spec`参数设置和各种用法。

**到此为止，15-24行都有了相应的输出，我们也了解到，在这段时期，gVisor进行了参数获取，然后运行create子命令，之后再 Load `spec`参数并在日志文件当中打印出来。**

接下来继续研究25行开始的输出。

首先是定位源代码的位置，通过`Create container`关键字，定位到了`runsc/container/container.go`文件的`Create()`函数中，找到输出语句，添加`Stack Trace`语句，如下所示

```go
func Create(id string, spec *specs.Spec, conf *boot.Config, bundleDir, consoleSocket, pidFile, userLog string) (*Container, error) {
	// add by zty in oder to watch stack trace
	// using "runtime/debug" PrintStack() -> Maybe use runtime.Stack() can collect the info and output to log
	debug.PrintStack()
	log.Debugf("Create container %q in root dir: %s", id, conf.RootDir)
	...
}
```

根据文件名可以知道，这是一个关于`container`的文件，做的事情不外乎是初始化还有一系列的设置。再次运行之后我们得到了输出的`Stack Trace`

```
goroutine 1 [running]:
runtime/debug.Stack(0x100, 0x21, 0x1e)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/container.Create(0x7ffc62361e4a, 0x40, 0xc00008f1f0, 0xc0001a6000, 0x7ffc62361d4c, 0x74, 0x0, 0x0, 0x7ffc62361dcc, 0x7d, ...)
	runsc/container/container.go:253 +0x4e
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc0000ba900, 0xe384c0, 0xc000094010, 0xc0001945a0, 0xc000158aa0, 0x2, 0x2, 0x7fabe090e6d0)
	runsc/cmd/create.go:99 +0x203
github.com/google/subcommands.(*Commander).Execute(0xc00008e070, 0xe384c0, 0xc000094010, 0xc000158aa0, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:245 +0x1452
```

可以看到`container.Create`的调用者，仍然是`create.go`文件，继续回到上文当中关于该文件的节选，可以发现，调用正是以下几句

```go
	// Create the container. A new sandbox will be created for the
	// container unless the metadata specifies that it should be run in an
	// existing container.
	if _, err := container.Create(id, spec, conf, bundleDir, c.consoleSocket, c.pidFile, c.userLog); err != nil {
		Fatalf("creating container: %v", err)
	}
```

这里调用了`container.Create()`函数，创建了`container`，同时还有对应的`sandbox`。

接下来仔细分析一下，在`container.Create()`函数里面到底做了什么

```go
// Create creates the container in a new Sandbox process, unless the metadata
// indicates that an existing Sandbox should be used. The caller must call
// Destroy() on the container.
func Create(id string, spec *specs.Spec, conf *boot.Config, bundleDir, consoleSocket, pidFile, userLog string) (*Container, error) {
	// add by zty in oder to watch stack trace
	// using "runtime/debug" PrintStack() -> Maybe use runtime.Stack() can collect the info and output to log
	debug.PrintStack()
	log.Debugf("Create container %q in root dir: %s", id, conf.RootDir)
	if err := validateID(id); err != nil {
		return nil, err
	}

	unlockRoot, err := maybeLockRootContainer(spec, conf.RootDir)
	if err != nil {
		return nil, err
	}
	defer unlockRoot()

	// Lock the container metadata file to prevent concurrent creations of
	// containers with the same id.
	containerRoot := filepath.Join(conf.RootDir, id)
	unlock, err := lockContainerMetadata(containerRoot)
	if err != nil {
		return nil, err
	}
	defer unlock()

	// Check if the container already exists by looking for the metadata
	// file.
	if _, err := os.Stat(filepath.Join(containerRoot, metadataFilename)); err == nil {
		return nil, fmt.Errorf("container with id %q already exists", id)
	} else if !os.IsNotExist(err) {
		return nil, fmt.Errorf("looking for existing container in %q: %v", containerRoot, err)
	}

	c := &Container{
		ID:               id,
		Spec:             spec,
		ConsoleSocket:    consoleSocket,
		BundleDir:        bundleDir,
		Root:             containerRoot,
		Status:           Creating,
		CreatedAt:        time.Now(),
		Owner:            os.Getenv("USER"),
		RootContainerDir: conf.RootDir,
	}
	// The Cleanup object cleans up partially created containers when an error occurs.
	// Any errors occuring during cleanup itself are ignored.
	cu := specutils.MakeCleanup(func() { _ = c.Destroy() })
	defer cu.Clean()

	// If the metadata annotations indicate that this container should be
	// started in an existing sandbox, we must do so. The metadata will
	// indicate the ID of the sandbox, which is the same as the ID of the
	// init container in the sandbox.
	if isRoot(spec) {
		log.Debugf("Creating new sandbox for container %q", id)

		// Create and join cgroup before processes are created to ensure they are
		// part of the cgroup from the start (and all tneir children processes).
		cg, err := cgroup.New(spec)
		if err != nil {
			return nil, err
		}
		if cg != nil {
			// If there is cgroup config, install it before creating sandbox process.
			if err := cg.Install(spec.Linux.Resources); err != nil {
				return nil, fmt.Errorf("configuring cgroup: %v", err)
			}
		}
		if err := runInCgroup(cg, func() error {
			ioFiles, specFile, err := c.createGoferProcess(spec, conf, bundleDir)
			if err != nil {
				return err
			}

			// Start a new sandbox for this container. Any errors after this point
			// must destroy the container.
			c.Sandbox, err = sandbox.New(id, spec, conf, bundleDir, consoleSocket, userLog, ioFiles, specFile, cg)
			return err
		}); err != nil {
			return nil, err
		}
	} else {
		// This is sort of confusing. For a sandbox with a root
		// container and a child container in it, runsc sees:
		// * A container struct whose sandbox ID is equal to the
		//   container ID. This is the root container that is tied to
		//   the creation of the sandbox.
		// * A container struct whose sandbox ID is equal to the above
		//   container/sandbox ID, but that has a different container
		//   ID. This is the child container.
		sbid, ok := specutils.SandboxID(spec)
		if !ok {
			return nil, fmt.Errorf("no sandbox ID found when creating container")
		}
		log.Debugf("Creating new container %q in sandbox %q", c.ID, sbid)

		// Find the sandbox associated with this ID.
		sb, err := Load(conf.RootDir, sbid)
		if err != nil {
			return nil, err
		}
		c.Sandbox = sb.Sandbox
		if err := c.Sandbox.CreateContainer(c.ID); err != nil {
			return nil, err
		}
	}
	c.changeStatus(Created)

	// Save the metadata file.
	if err := c.save(); err != nil {
		return nil, err
	}

	// Write the PID file. Containerd considers the create complete after
	// this file is created, so it must be the last thing we do.
	if pidFile != "" {
		if err := ioutil.WriteFile(pidFile, []byte(strconv.Itoa(c.SandboxPid())), 0644); err != nil {
			return nil, fmt.Errorf("error writing PID file: %v", err)
		}
	}

	cu.Release()
	return c, nil
}
```

这个函数首先检查了各种参数的合法性，之后根据是否有指定的`sandbox`来配备sandbox，同时还调用了`cgroup.New()`来创建`cgroup`并调用`cg.Install()`来配置`cgroup`，`sandbox.New()`函数或者是`c.Sandbox.CreateContainer()`函数，按照情况来新建`sandbox`进程或者是利用已有`sandbox`进程来配置`container`。

日志文件的27-38行就是在调用`cgroup.New()`函数的时候写下的，该函数位于`runsc/cgroup/cgroup.go`。

##### `gofer`的创建和初始化。

通过定位输出语句，定位到`runsc/container/container.go`中的`createGoferProcess()`函数，插入`debug.PrintStack()`，再次运行得到以下输出

```
goroutine 1 [running]:
runtime/debug.Stack(0x1d, 0x24, 0x0)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/container.(*Container).createGoferProcess(0xc0000be780, 0xc000033260, 0xc000164000, 0x7ffd117f8d4c, 0x74, 0x0, 0x0, 0x0, 0x0, 0x0, ...)
	runsc/container/container.go:935 +0xda6
gvisor.googlesource.com/gvisor/runsc/container.Create.func2(0xc000000000, 0xc0001cec00)
	runsc/container/container.go:318 +0xdc
gvisor.googlesource.com/gvisor/runsc/container.runInCgroup(0xc0001ceb80, 0xc00018fc20, 0x0, 0x0)
	runsc/container/container.go:1061 +0x97
gvisor.googlesource.com/gvisor/runsc/container.Create(0x7ffd117f8e4a, 0x40, 0xc000033260, 0xc000164000, 0x7ffd117f8d4c, 0x74, 0x0, 0x0, 0x7ffd117f8dcc, 0x7d, ...)
	runsc/container/container.go:317 +0x7c0
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc000070940, 0xe384c0, 0xc00003a028, 0xc000152600, 0xc000122aa0, 0x2, 0x2, 0x7f72e4404008)
	runsc/cmd/create.go:99 +0x203
github.com/google/subcommands.(*Commander).Execute(0xc0000320e0, 0xe384c0, 0xc00003a028, 0xc000122aa0, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:245 +0x1452
```

中间出现了`runInCgroup`,`func2`这样的函数，这是因为在调用`createGoferProcess()`的时候是在`cgroup`当中进行的，所以才会有这样的嵌套调用，**有时间要研究一下cgroup和gvisor的关系**，`createGoferProcess()`的具体内容如下所示

```go
func (c *Container) createGoferProcess(spec *specs.Spec, conf *boot.Config, bundleDir string) ([]*os.File, *os.File, error) {
	// Start with the general config flags.
	args := conf.ToFlags()
    
	var goferEnds []*os.File

	// nextFD is the next available file descriptor for the gofer process.
	// It starts at 3 because 0-2 are used by stdin/stdout/stderr.
	nextFD := 3

	if conf.LogFilename != "" {
		logFile, err := os.OpenFile(conf.LogFilename, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
		if err != nil {
			return nil, nil, fmt.Errorf("opening log file %q: %v", conf.LogFilename, err)
		}
		defer logFile.Close()
		goferEnds = append(goferEnds, logFile)
		args = append(args, "--log-fd="+strconv.Itoa(nextFD))
		nextFD++
	}

	if conf.DebugLog != "" {
		debugLogFile, err := specutils.DebugLogFile(conf.DebugLog, "gofer")
		if err != nil {
			return nil, nil, fmt.Errorf("opening debug log file in %q: %v", conf.DebugLog, err)
		}
		defer debugLogFile.Close()
		goferEnds = append(goferEnds, debugLogFile)
		args = append(args, "--debug-log-fd="+strconv.Itoa(nextFD))
		nextFD++
	}

	args = append(args, "gofer", "--bundle", bundleDir)
	if conf.Overlay {
		args = append(args, "--panic-on-write=true")
	}

	// Open the spec file to donate to the sandbox.
	specFile, err := specutils.OpenSpec(bundleDir)
	if err != nil {
		return nil, nil, fmt.Errorf("opening spec file: %v", err)
	}
	defer specFile.Close()
	goferEnds = append(goferEnds, specFile)
	args = append(args, "--spec-fd="+strconv.Itoa(nextFD))
	nextFD++

	// Create pipe that allows gofer to send mount list to sandbox after all paths
	// have been resolved.
	mountsSand, mountsGofer, err := os.Pipe()
	if err != nil {
		return nil, nil, err
	}
	defer mountsGofer.Close()
	goferEnds = append(goferEnds, mountsGofer)
	args = append(args, fmt.Sprintf("--mounts-fd=%d", nextFD))
	nextFD++

	// Add root mount and then add any other additional mounts.
	mountCount := 1
	for _, m := range spec.Mounts {
		if specutils.Is9PMount(m) {
			mountCount++
		}
	}

	sandEnds := make([]*os.File, 0, mountCount)
	for i := 0; i < mountCount; i++ {
		fds, err := syscall.Socketpair(syscall.AF_UNIX, syscall.SOCK_STREAM|syscall.SOCK_CLOEXEC, 0)
		if err != nil {
			return nil, nil, err
		}
		sandEnds = append(sandEnds, os.NewFile(uintptr(fds[0]), "sandbox IO FD"))

		goferEnd := os.NewFile(uintptr(fds[1]), "gofer IO FD")
		defer goferEnd.Close()
		goferEnds = append(goferEnds, goferEnd)

		args = append(args, fmt.Sprintf("--io-fds=%d", nextFD))
		nextFD++
	}

	binPath := specutils.ExePath
	cmd := exec.Command(binPath, args...)
	cmd.ExtraFiles = goferEnds
	cmd.Args[0] = "runsc-gofer"

	// Enter new namespaces to isolate from the rest of the system. Don't unshare
	// cgroup because gofer is added to a cgroup in the caller's namespace.
	nss := []specs.LinuxNamespace{
		{Type: specs.IPCNamespace},
		{Type: specs.MountNamespace},
		{Type: specs.NetworkNamespace},
		{Type: specs.PIDNamespace},
		{Type: specs.UTSNamespace},
	}

	// Setup any uid/gid mappings, and create or join the configured user
	// namespace so the gofer's view of the filesystem aligns with the
	// users in the sandbox.
	userNS := specutils.FilterNS([]specs.LinuxNamespaceType{specs.UserNamespace}, spec)
	nss = append(nss, userNS...)
	specutils.SetUIDGIDMappings(cmd, spec)
	if len(userNS) != 0 {
		// We need to set UID and GID to have capabilities in a new user namespace.
		cmd.SysProcAttr.Credential = &syscall.Credential{Uid: 0, Gid: 0}
	}

	// add by zty in oder to watch stack trace
	// using "runtime/debug" PrintStack() -> Maybe use runtime.Stack() can collect the info and output to log
	// debug.PrintStack()

	// Start the gofer in the given namespace.
	log.Debugf("Starting gofer: %s %v", binPath, args)
	if err := specutils.StartInNS(cmd, nss); err != nil {
		return nil, nil, fmt.Errorf("Gofer: %v", err)
	}
	log.Infof("Gofer started, PID: %d", cmd.Process.Pid)
	c.GoferPid = cmd.Process.Pid
	c.goferIsChild = true
	return sandEnds, mountsSand, nil
}
```

×××待完善

由于时间限制和对Go语言用法的不太了解，这里能够解读出来的就是根据之前的一些参数设置，来初始化**gofer**进程，可以看到在该文件当中第115行才是真正创建进程的语句，之前的都是对于参数的一些设置，包括了命名空间**namespace**。

第115行中的`specutils.StartInNS(cmd, nss)`来自于`/runsc/specutils/namespace.go`，主要的作用是**joins or creates the given namespaces and calls cmd.Start before restoring the namespaces to the original values.** 可以看到一共做了两件事，一个是关于namespace的，一个是关于命令执行的，在这里也就是创建gofer进程。第83-86行定义了`cmd`参数。

×××

由于重新运行了一个命令，因此生成的log文件会有所不通，因为所属的进程不同，现在去看一下`.gofer`结尾的日志文件的开头部分，或许可以发现一些相关的信息。

```
I0613 14:29:18.198405       1 x:0] ***************************
I0613 14:29:18.198480       1 x:0] Args: [runsc-gofer --root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 gofer --bundle /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --spec-fd=5 --mounts-fd=6 --io-fds=7 --io-fds=8 --io-fds=9 --io-fds=10]
I0613 14:29:18.198505       1 x:0] Version release-20190529.1-39-ge03aefd88c42-dirty
I0613 14:29:18.198512       1 x:0] PID: 1
I0613 14:29:18.198520       1 x:0] UID: 0, GID: 0
I0613 14:29:18.198526       1 x:0] Configuration:
I0613 14:29:18.198531       1 x:0] 		RootDir: /var/run/docker/runtime-runsc-kvm/moby
I0613 14:29:18.198537       1 x:0] 		Platform: kvm
I0613 14:29:18.198567       1 x:0] 		FileAccess: exclusive, overlay: false
I0613 14:29:18.198576       1 x:0] 		Network: sandbox, logging: false
I0613 14:29:18.198584       1 x:0] 		Strace: true, max size: 1024, syscalls: []
I0613 14:29:18.198592       1 x:0] ***************************
```

这里截取了`.gofer`文件的开头12行，也就是`/runsc/main.go`的输出内容，和`.create`文件类似，也有一样的输出，因为命令执行的入口是这里，所以会有参数的打印，这里主要看一下两个文件不同的地方。首先是参数`Args`，为了方便对照，下面是两个文件的对比。

```
# .create
Args: [/usr/local/bin/runsc --platform=kvm --debug-log=/tmp/runsc/ --debug --strace --root /var/run/docker/runtime-runsc-kvm/moby --log /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format json create --bundle /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --pid-file /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/init.pid 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222]

# .gofer
Args: [runsc-gofer --root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 gofer --bundle /run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --spec-fd=5 --mounts-fd=6 --io-fds=7 --io-fds=8 --io-fds=9 --io-fds=10]
```

虽然参数的内容很长，但是可以发现第一组`Args`名称是`runsc`，命令是`create`，第二组`Args`名称是`runsc-gofer`，命令为`gofer`。

类似的参数应该在其他日志文件当中也可以找到，所有的命令应该都在`/runsc/cmd/`下有定义，在这里就是`create.go`和`gofer.go`这两个文件定义的命令。

在这个日志文件当中还需要注意到的一点是，`PID`这个参数的值为`1`。

在`Linux kernel`中，`0`号进程是`scheduler`，`1`号进程是`init/systemd`（所有`user thread`的祖先），`2`号进程是`[kthreadd]`（所有`kernel thread`的父进程）。

所以这个gofer进程应该是在新的namespace里面运行的，是对外隔离的。

实际上这个gofer进程在host kernal里的真实`PID`，应该是在`.create`文件当中输出的值。

到此为止gofer进程就已经被创建了。关于gofer的研究不在这篇文章的探讨范围，后续还会有更为详细的研究。



**Sandbox的创建**

和gofer一样，sandbox的创建同样也是在cgroup中的，再次通过输出语句定位到sandbox的日志输出代码处，加入stack trace语句得到以下输出

```
goroutine 1 [running]:
runtime/debug.Stack(0x8f554c, 0xc0001f2750, 0xe)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/sandbox.(*Sandbox).createSandboxProcess(0xc0001f2720, 0xc0000331f0, 0xc000178000, 0x7ffe1de2ad4c, 0x74, 0x0, 0x0, 0x0, 0x0, 0xc0001e4d40, ...)
	runsc/sandbox/sandbox.go:339 +0x2b3
gvisor.googlesource.com/gvisor/runsc/sandbox.New(0x7ffe1de2ae4a, 0x40, 0xc0000331f0, 0xc000178000, 0x7ffe1de2ad4c, 0x74, 0x0, 0x0, 0x0, 0x0, ...)
	runsc/sandbox/sandbox.go:98 +0x35c
gvisor.googlesource.com/gvisor/runsc/container.Create.func2(0xc000000000, 0xc0001e4ba0)
	runsc/container/container.go:325 +0x1aa
gvisor.googlesource.com/gvisor/runsc/container.runInCgroup(0xc0001e4b20, 0xc0001a3c20, 0x0, 0x0)
	runsc/container/container.go:1061 +0x97
gvisor.googlesource.com/gvisor/runsc/container.Create(0x7ffe1de2ae4a, 0x40, 0xc0000331f0, 0xc000178000, 0x7ffe1de2ad4c, 0x74, 0x0, 0x0, 0x7ffe1de2adcc, 0x7d, ...)
	runsc/container/container.go:317 +0x7c0
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc000070940, 0xe384c0, 0xc00003a028, 0xc000164600, 0xc00013aa40, 0x2, 0x2, 0x7f1a8736e008)
	runsc/cmd/create.go:99 +0x203
github.com/google/subcommands.(*Commander).Execute(0xc000032070, 0xe384c0, 0xc00003a028, 0xc00013aa40, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:251 +0x1452
```

由于sandbox的创建命令是和gofer的创建命令位于同一个函数里面，所以调用关系也是类似的，同样是在container的创建过程当中实现的，定位到`container.go`文件当中的`Create()`函数中，有如下语句

```
	// Start a new sandbox for this container. Any errors after this point
	// must destroy the container.
	c.Sandbox, err = sandbox.New(id, spec, conf, bundleDir, consoleSocket, userLog, ioFiles, specFile, cg)
```

这边是sandbox的创建命令，可以看到是调用了`/runsc/sandbox/sandbox.go`中的`sandbox.New()`函数。

```go
// createSandboxProcess starts the sandbox as a subprocess by running the "boot"
// command, passing in the bundle dir.
func (s *Sandbox) createSandboxProcess(spec *specs.Spec, conf *boot.Config, bundleDir, consoleSocket, userLog string, ioFiles []*os.File, mountsFile, startSyncFile *os.File) error {
	// nextFD is used to get unused FDs that we can pass to the sandbox.  It
	// starts at 3 because 0, 1, and 2 are taken by stdin/out/err.
	nextFD := 3

	binPath := specutils.ExePath
	cmd := exec.Command(binPath, conf.ToFlags()...)
	cmd.SysProcAttr = &syscall.SysProcAttr{}

	// Open the log files to pass to the sandbox as FDs.
	//
	// These flags must come BEFORE the "boot" command in cmd.Args.
	if conf.LogFilename != "" {
		logFile, err := os.OpenFile(conf.LogFilename, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
		if err != nil {
			return fmt.Errorf("opening log file %q: %v", conf.LogFilename, err)
		}
		defer logFile.Close()
		cmd.ExtraFiles = append(cmd.ExtraFiles, logFile)
		cmd.Args = append(cmd.Args, "--log-fd="+strconv.Itoa(nextFD))
		nextFD++
	}
	if conf.DebugLog != "" {
		debugLogFile, err := specutils.DebugLogFile(conf.DebugLog, "boot")
		if err != nil {
			return fmt.Errorf("opening debug log file in %q: %v", conf.DebugLog, err)
		}
		defer debugLogFile.Close()
		cmd.ExtraFiles = append(cmd.ExtraFiles, debugLogFile)
		cmd.Args = append(cmd.Args, "--debug-log-fd="+strconv.Itoa(nextFD))
		nextFD++
	}

	// Add the "boot" command to the args.
	//
	// All flags after this must be for the boot command
	cmd.Args = append(cmd.Args, "boot", "--bundle="+bundleDir)

	// Create a socket for the control server and donate it to the sandbox.
	addr := boot.ControlSocketAddr(s.ID)
	sockFD, err := server.CreateSocket(addr)
	// add by zty in oder to watch stack trace
	// using "runtime/debug" PrintStack() -> Maybe use runtime.Stack() can collect the info and output to log
	debug.PrintStack()
	log.Infof("Creating sandbox process with addr: %s", addr[1:]) // skip "\00".
	if err != nil {
		return fmt.Errorf("creating control server socket for sandbox %q: %v", s.ID, err)
	}
	controllerFile := os.NewFile(uintptr(sockFD), "control_server_socket")
	defer controllerFile.Close()
	cmd.ExtraFiles = append(cmd.ExtraFiles, controllerFile)
	cmd.Args = append(cmd.Args, "--controller-fd="+strconv.Itoa(nextFD))
	nextFD++

	defer mountsFile.Close()
	cmd.ExtraFiles = append(cmd.ExtraFiles, mountsFile)
	cmd.Args = append(cmd.Args, "--mounts-fd="+strconv.Itoa(nextFD))
	nextFD++

	specFile, err := specutils.OpenSpec(bundleDir)
	if err != nil {
		return err
	}
	defer specFile.Close()
	cmd.ExtraFiles = append(cmd.ExtraFiles, specFile)
	cmd.Args = append(cmd.Args, "--spec-fd="+strconv.Itoa(nextFD))
	nextFD++

	cmd.ExtraFiles = append(cmd.ExtraFiles, startSyncFile)
	cmd.Args = append(cmd.Args, "--start-sync-fd="+strconv.Itoa(nextFD))
	nextFD++

	// If there is a gofer, sends all socket ends to the sandbox.
	for _, f := range ioFiles {
		defer f.Close()
		cmd.ExtraFiles = append(cmd.ExtraFiles, f)
		cmd.Args = append(cmd.Args, "--io-fds="+strconv.Itoa(nextFD))
		nextFD++
	}

	// If the platform needs a device FD we must pass it in.
	if deviceFile, err := deviceFileForPlatform(conf.Platform); err != nil {
		return err
	} else if deviceFile != nil {
		defer deviceFile.Close()
		cmd.ExtraFiles = append(cmd.ExtraFiles, deviceFile)
		cmd.Args = append(cmd.Args, "--device-fd="+strconv.Itoa(nextFD))
		nextFD++
	}

	// The current process' stdio must be passed to the application via the
	// --stdio-fds flag. The stdio of the sandbox process itself must not
	// be connected to the same FDs, otherwise we risk leaking sandbox
	// errors to the application, so we set the sandbox stdio to nil,
	// causing them to read/write from the null device.
	cmd.Stdin = nil
	cmd.Stdout = nil
	cmd.Stderr = nil

	// If the console control socket file is provided, then create a new
	// pty master/slave pair and set the TTY on the sandbox process.
	if consoleSocket != "" {
		cmd.Args = append(cmd.Args, "--console=true")

		// console.NewWithSocket will send the master on the given
		// socket, and return the slave.
		tty, err := console.NewWithSocket(consoleSocket)
		if err != nil {
			return fmt.Errorf("setting up console with socket %q: %v", consoleSocket, err)
		}
		defer tty.Close()

		// Set the TTY as a controlling TTY on the sandbox process.
		// Note that the Ctty field must be the FD of the TTY in the
		// *new* process, not this process. Since we are about to
		// assign the TTY to nextFD, we can use that value here.
		// stdin, we can use FD 0 here.
		cmd.SysProcAttr.Setctty = true
		cmd.SysProcAttr.Ctty = nextFD

		// Pass the tty as all stdio fds to sandbox.
		for i := 0; i < 3; i++ {
			cmd.ExtraFiles = append(cmd.ExtraFiles, tty)
			cmd.Args = append(cmd.Args, "--stdio-fds="+strconv.Itoa(nextFD))
			nextFD++
		}

		if conf.Debug {
			// If debugging, send the boot process stdio to the
			// TTY, so that it is easier to find.
			cmd.Stdin = tty
			cmd.Stdout = tty
			cmd.Stderr = tty
		}
	} else {
		// If not using a console, pass our current stdio as the
		// container stdio via flags.
		for _, f := range []*os.File{os.Stdin, os.Stdout, os.Stderr} {
			cmd.ExtraFiles = append(cmd.ExtraFiles, f)
			cmd.Args = append(cmd.Args, "--stdio-fds="+strconv.Itoa(nextFD))
			nextFD++
		}

		if conf.Debug {
			// If debugging, send the boot process stdio to the
			// this process' stdio, so that is is easier to find.
			cmd.Stdin = os.Stdin
			cmd.Stdout = os.Stdout
			cmd.Stderr = os.Stderr
		}
	}

	// Detach from this session, otherwise cmd will get SIGHUP and SIGCONT
	// when re-parented.
	cmd.SysProcAttr.Setsid = true

	// nss is the set of namespaces to join or create before starting the sandbox
	// process. Mount, IPC and UTS namespaces from the host are not used as they
	// are virtualized inside the sandbox. Be paranoid and run inside an empty
	// namespace for these. Don't unshare cgroup because sandbox is added to a
	// cgroup in the caller's namespace.
	log.Infof("Sandbox will be started in new mount, IPC and UTS namespaces")
	nss := []specs.LinuxNamespace{
		{Type: specs.IPCNamespace},
		{Type: specs.MountNamespace},
		{Type: specs.UTSNamespace},
	}

	if conf.Platform == boot.PlatformPtrace {
		// TODO(b/75837838): Also set a new PID namespace so that we limit
		// access to other host processes.
		log.Infof("Sandbox will be started in the current PID namespace")
	} else {
		log.Infof("Sandbox will be started in a new PID namespace")
		nss = append(nss, specs.LinuxNamespace{Type: specs.PIDNamespace})
		cmd.Args = append(cmd.Args, "--pidns=true")
	}

	// Joins the network namespace if network is enabled. the sandbox talks
	// directly to the host network, which may have been configured in the
	// namespace.
	if ns, ok := specutils.GetNS(specs.NetworkNamespace, spec); ok && conf.Network != boot.NetworkNone {
		log.Infof("Sandbox will be started in the container's network namespace: %+v", ns)
		nss = append(nss, ns)
	} else if conf.Network == boot.NetworkHost {
		log.Infof("Sandbox will be started in the host network namespace")
	} else {
		log.Infof("Sandbox will be started in new network namespace")
		nss = append(nss, specs.LinuxNamespace{Type: specs.NetworkNamespace})
	}

	// User namespace depends on the network type. Host network requires to run
	// inside the user namespace specified in the spec or the current namespace
	// if none is configured.
	if conf.Network == boot.NetworkHost {
		if userns, ok := specutils.GetNS(specs.UserNamespace, spec); ok {
			log.Infof("Sandbox will be started in container's user namespace: %+v", userns)
			nss = append(nss, userns)
			specutils.SetUIDGIDMappings(cmd, spec)
		} else {
			log.Infof("Sandbox will be started in the current user namespace")
		}
		// When running in the caller's defined user namespace, apply the same
		// capabilities to the sandbox process to ensure it abides to the same
		// rules.
		cmd.Args = append(cmd.Args, "--apply-caps=true")

		// If we have CAP_SYS_ADMIN, we can create an empty chroot and
		// bind-mount the executable inside it.
		if conf.TestOnlyAllowRunAsCurrentUserWithoutChroot {
			log.Warningf("Running sandbox in test mode without chroot. This is only safe in tests!")

		} else if specutils.HasCapabilities(capability.CAP_SYS_ADMIN) {
			log.Infof("Sandbox will be started in minimal chroot")
			cmd.Args = append(cmd.Args, "--setup-root")
		} else {
			return fmt.Errorf("can't run sandbox process in minimal chroot since we don't have CAP_SYS_ADMIN")
		}
	} else {
		// If we have CAP_SETUID and CAP_SETGID, then we can also run
		// as user nobody.
		if conf.TestOnlyAllowRunAsCurrentUserWithoutChroot {
			log.Warningf("Running sandbox in test mode as current user (uid=%d gid=%d). This is only safe in tests!", os.Getuid(), os.Getgid())
			log.Warningf("Running sandbox in test mode without chroot. This is only safe in tests!")
		} else if specutils.HasCapabilities(capability.CAP_SETUID, capability.CAP_SETGID) {
			log.Infof("Sandbox will be started in new user namespace")
			nss = append(nss, specs.LinuxNamespace{Type: specs.UserNamespace})

			// Map nobody in the new namespace to nobody in the parent namespace.
			//
			// A sandbox process will construct an empty
			// root for itself, so it has to have the CAP_SYS_ADMIN
			// capability.
			//
			// FIXME(b/122554829): The current implementations of
			// os/exec doesn't allow to set ambient capabilities if
			// a process is started in a new user namespace. As a
			// workaround, we start the sandbox process with the 0
			// UID and then it constructs a chroot and sets UID to
			// nobody.  https://github.com/golang/go/issues/2315
			const nobody = 65534
			cmd.SysProcAttr.UidMappings = []syscall.SysProcIDMap{
				{
					ContainerID: int(0),
					HostID:      int(nobody - 1),
					Size:        int(1),
				},
				{
					ContainerID: int(nobody),
					HostID:      int(nobody),
					Size:        int(1),
				},
			}
			cmd.SysProcAttr.GidMappings = []syscall.SysProcIDMap{
				{
					ContainerID: int(nobody),
					HostID:      int(nobody),
					Size:        int(1),
				},
			}

			// Set credentials to run as user and group nobody.
			cmd.SysProcAttr.Credential = &syscall.Credential{
				Uid: 0,
				Gid: nobody,
			}
			cmd.Args = append(cmd.Args, "--setup-root")
		} else {
			return fmt.Errorf("can't run sandbox process as user nobody since we don't have CAP_SETUID or CAP_SETGID")
		}
	}

	cmd.Args[0] = "runsc-sandbox"

	if s.Cgroup != nil {
		cpuNum, err := s.Cgroup.NumCPU()
		if err != nil {
			return fmt.Errorf("getting cpu count from cgroups: %v", err)
		}
		cmd.Args = append(cmd.Args, "--cpu-num", strconv.Itoa(cpuNum))

		mem, err := s.Cgroup.MemoryLimit()
		if err != nil {
			return fmt.Errorf("getting memory limit from cgroups: %v", err)
		}
		// When memory limit is unset, a "large" number is returned. In that case,
		// just stick with the default.
		if mem < 0x7ffffffffffff000 {
			cmd.Args = append(cmd.Args, "--total-memory", strconv.FormatUint(mem, 10))
		}
	}

	if userLog != "" {
		f, err := os.OpenFile(userLog, os.O_WRONLY|os.O_CREATE|os.O_APPEND, 0664)
		if err != nil {
			return fmt.Errorf("opening compat log file: %v", err)
		}
		defer f.Close()

		cmd.ExtraFiles = append(cmd.ExtraFiles, f)
		cmd.Args = append(cmd.Args, "--user-log-fd", strconv.Itoa(nextFD))
		nextFD++
	}

	// Add container as the last argument.
	cmd.Args = append(cmd.Args, s.ID)

	// Log the FDs we are donating to the sandbox process.
	for i, f := range cmd.ExtraFiles {
		log.Debugf("Donating FD %d: %q", i+3, f.Name())
	}

	log.Debugf("Starting sandbox: %s %v", binPath, cmd.Args)
	log.Debugf("SysProcAttr: %+v", cmd.SysProcAttr)
	if err := specutils.StartInNS(cmd, nss); err != nil {
		return fmt.Errorf("Sandbox: %v", err)
	}
	s.child = true
	s.Pid = cmd.Process.Pid
	log.Infof("Sandbox started, PID: %d", s.Pid)
	// add by zty in oder to watch stack trace
	// debug.PrintStack()
	return nil
}
```

×××待完善

和之前的`createGoferProcess()`函数非常类似，都是先定义`cmd`和`nss`，然后调用`specutils.StartInNS(cmd, nss)`来执行命令，那么和之前的gofer进程一样，也会有一个新的进程运行，这个进程的参数应该包含了代码当中所提到的一系列内容，即利用`cmd.Args = append(cmd.Args, "--setup-root")`这样语句来实现的。

通过阅读相关的日志文件，基本上锁定了`.boot`文件即为创建`sandbox`时产生的日志文件，该文件的开头如下所示

```
I0613 14:29:18.204655       1 x:0] ***************************
I0613 14:29:18.204724       1 x:0] Args: [runsc-sandbox --root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 boot --bundle=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --controller-fd=5 --mounts-fd=6 --spec-fd=7 --start-sync-fd=8 --io-fds=9 --io-fds=10 --io-fds=11 --io-fds=12 --device-fd=13 --stdio-fds=14 --stdio-fds=15 --stdio-fds=16 --pidns=true --setup-root --cpu-num 4 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222]
I0613 14:29:18.204746       1 x:0] Version release-20190529.1-39-ge03aefd88c42-dirty
I0613 14:29:18.204753       1 x:0] PID: 1
I0613 14:29:18.204761       1 x:0] UID: 0, GID: 65534
I0613 14:29:18.204772       1 x:0] Configuration:
I0613 14:29:18.204778       1 x:0] 		RootDir: /var/run/docker/runtime-runsc-kvm/moby
I0613 14:29:18.204784       1 x:0] 		Platform: kvm
I0613 14:29:18.204791       1 x:0] 		FileAccess: exclusive, overlay: false
I0613 14:29:18.204798       1 x:0] 		Network: sandbox, logging: false
I0613 14:29:18.204805       1 x:0] 		Strace: true, max size: 1024, syscalls: []
I0613 14:29:18.204812       1 x:0] ***************************
```

它的`PID`也是1，而命令为`boot`，同时这里和之前两个文件的不同的是，`GID`为65534。这些都应该是有原因的，需要后续去详细研究。

×××

**余下的部分**

再次利用关键字定位代码，找到了余下的日志输出位置，是位于`runsc/cgroup/cgroup.go`中的`Join()`函数内，`Join()`函数的说明为`Join adds the current process to the all controllers. Returns function that restores cgroup to the original state.`

插入`stack trace`代码之后的输出如下

```
goroutine 1 [running]:
runtime/debug.Stack(0x7ffe1874bd4c, 0x74, 0x0)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/cgroup.(*Cgroup).Join.func2()
	runsc/cgroup/cgroup.go:312 +0x7e
gvisor.googlesource.com/gvisor/runsc/container.runInCgroup(0xc000212b00, 0xc0001cfc20, 0x0, 0x0)
	runsc/container/container.go:1061 +0xab
gvisor.googlesource.com/gvisor/runsc/container.Create(0x7ffe1874be4a, 0x40, 0xc00008f180, 0xc0001a2000, 0x7ffe1874bd4c, 0x74, 0x0, 0x0, 0x7ffe1874bdcc, 0x7d, ...)
	runsc/container/container.go:317 +0x7c0
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc0000b8900, 0xe384c0, 0xc00003a028, 0xc00019e420, 0xc000154a20, 0x2, 0x2, 0x7f83806c76d0)
	runsc/cmd/create.go:99 +0x203
github.com/google/subcommands.(*Commander).Execute(0xc000032070, 0xe384c0, 0xc00003a028, 0xc000154a20, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:251 +0x1452
```

这里会有一些奇怪的点，就是`cgroup.Join()`函数为何会在这个时候被调用。

其实调用的不是`cgroup.Join()`函数，而是在函数当中被定义和返回的`func()`

```go
	// Replace empty undo with the real thing before changes are made to cgroups.
	undo = func() {
		for _, path := range undoPaths {
			log.Debugf("Restoring cgroup %q", path)
			if err := setValue(path, "cgroup.procs", "0"); err != nil {
				log.Warningf("Error restoring cgroup %q: %v", path, err)
			}
		}
	}
	...
	return undo, nil
```

因此当执行完`createGoferProcess`和`sandbox.New`这两个操作之后，就调用了该函数。

之后的语句通过stack trace得到

```
goroutine 1 [running]:
runtime/debug.Stack(0xc000035c50, 0xc0001d6260, 0x16)
	GOROOT/src/runtime/debug/stack.go:24 +0x9d
runtime/debug.PrintStack()
	GOROOT/src/runtime/debug/stack.go:16 +0x22
gvisor.googlesource.com/gvisor/runsc/container.(*Container).save(0xc0000be780, 0x0, 0x0)
	runsc/container/container.go:741 +0x34
gvisor.googlesource.com/gvisor/runsc/container.Create(0x7ffc958ffe4a, 0x40, 0xc000033260, 0xc000164000, 0x7ffc958ffd4c, 0x74, 0x0, 0x0, 0x7ffc958ffdcc, 0x7d, ...)
	runsc/container/container.go:358 +0x7fe
gvisor.googlesource.com/gvisor/runsc/cmd.(*Create).Execute(0xc000070940, 0xe384c0, 0xc00003a028, 0xc000150600, 0xc000122aa0, 0x2, 0x2, 0x7f62de9c7008)
	runsc/cmd/create.go:99 +0x203
github.com/google/subcommands.(*Commander).Execute(0xc0000320e0, 0xe384c0, 0xc00003a028, 0xc000122aa0, 0x2, 0x2, 0x0)
	external/com_github_google_subcommands/subcommands.go:141 +0x2fb
github.com/google/subcommands.Execute(...)
	external/com_github_google_subcommands/subcommands.go:371
main.main()
	runsc/main.go:251 +0x1452
```

是在`container.Create()`中发生的调用，保存container的相关信息

```go
	// Save the metadata file.
	if err := c.save(); err != nil {
		return nil, err
	}
```

最后一句即container进程结束之后，主进程接收到返回之后输出的日志。

```go
// main.go
	if subcmdCode == subcommands.ExitSuccess {
		log.Infof("Exiting with status: %v", ws)
		if ws.Signaled() {
			// No good way to return it, emulate what the shell does. Maybe raise
			// signall to self?
			os.Exit(128 + int(ws.Signal()))
		}
		os.Exit(ws.ExitStatus())
	}
```

至此为止，gVisor关于`runsc`初始化的所有日志相关内容就全部结束了，但gViosr的核心还是在于`sentry`、`gofer`这些组件，需要进一步结合日志文件去了解，目前还并未知晓`sentry`对应的日志文件是什么，极有可能就是所谓的`sandbox`。

