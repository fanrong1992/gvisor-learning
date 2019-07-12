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
I0613 14:29:18.204832       1 x:0] Setting up sandbox chroot in "/tmp"
I0613 14:29:18.204874       1 x:0] Mounting "proc" at "/tmp/proc"
I0613 14:29:18.229310       1 x:0] Execve "/proc/self/exe" again, bye!
I0613 06:29:18.233034       1 x:0] ***************************
I0613 06:29:18.233082       1 x:0] Args: [runsc-sandbox --root=/var/run/docker/runtime-runsc-kvm/moby --debug=true --log=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/log.json --log-format=json --debug-log=/tmp/runsc/ --debug-log-format=text --file-access=exclusive --overlay=false --network=sandbox --log-packets=false --platform=kvm --strace=true --strace-syscalls= --strace-log-size=1024 --watchdog-action=LogWarning --panic-signal=-1 --profile=false --net-raw=false --log-fd=3 --debug-log-fd=4 boot --bundle=/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 --controller-fd=5 --mounts-fd=6 --spec-fd=7 --start-sync-fd=8 --io-fds=9 --io-fds=10 --io-fds=11 --io-fds=12 --device-fd=13 --stdio-fds=14 --stdio-fds=15 --stdio-fds=16 --pidns=true --cpu-num 4 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222]
I0613 06:29:18.233106       1 x:0] Version release-20190529.1-39-ge03aefd88c42-dirty
I0613 06:29:18.233112       1 x:0] PID: 1
I0613 06:29:18.233122       1 x:0] UID: 65534, GID: 65534
I0613 06:29:18.233128       1 x:0] Configuration:
I0613 06:29:18.233135       1 x:0] 		RootDir: /var/run/docker/runtime-runsc-kvm/moby
I0613 06:29:18.233140       1 x:0] 		Platform: kvm
I0613 06:29:18.233150       1 x:0] 		FileAccess: exclusive, overlay: false
I0613 06:29:18.233159       1 x:0] 		Network: sandbox, logging: false
I0613 06:29:18.233165       1 x:0] 		Strace: true, max size: 1024, syscalls: []
I0613 06:29:18.233173       1 x:0] ***************************
W0613 06:29:18.234123       1 x:0] AppArmor profile "docker-default" is being ignored
W0613 06:29:18.234136       1 x:0] Seccomp spec is being ignored
D0613 06:29:18.234145       1 x:0] Spec: &{Version:1.0.1-dev Process:0xc0001badd0 Root:0xc0001e2f60 Hostname:8496793d4d0a Mounts:[{Destination:/proc Type:proc Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/proc Options:[nosuid noexec nodev]} {Destination:/dev Type:tmpfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/tmpfs Options:[nosuid strictatime mode=755 size=65536k]} {Destination:/dev/pts Type:devpts Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/devpts Options:[nosuid noexec newinstance ptmxmode=0666 mode=0620 gid=5]} {Destination:/sys Type:sysfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/sysfs Options:[nosuid noexec nodev ro]} {Destination:/sys/fs/cgroup Type:cgroup Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/cgroup Options:[ro nosuid noexec nodev]} {Destination:/dev/mqueue Type:mqueue Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mqueue Options:[nosuid noexec nodev]} {Destination:/etc/resolv.conf Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/resolv.conf Options:[rbind rprivate]} {Destination:/etc/hostname Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hostname Options:[rbind rprivate]} {Destination:/etc/hosts Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hosts Options:[rbind rprivate]} {Destination:/dev/shm Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mounts/shm Options:[rbind rprivate]}] Hooks:0xc0001940f0 Annotations:map[] Linux:0xc0001b22a0 Solaris:<nil> Windows:<nil>}
D0613 06:29:18.234202       1 x:0] Spec.Hooks: &{Prestart:[{Path:/proc/1466/exe Args:[libnetwork-setkey 8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 e4fe84bac6dbdce3579344f7e93a7401bac629c608c19ed4d5dd939dbd287de0] Env:[] Timeout:<nil>}] Poststart:[] Poststop:[]}
D0613 06:29:18.234216       1 x:0] Spec.Linux: &{UIDMappings:[] GIDMappings:[] Sysctl:map[] Resources:0xc0001a2780 CgroupsPath:/docker/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222 Namespaces:[{Type:mount Path:} {Type:network Path:} {Type:uts Path:} {Type:pid Path:} {Type:ipc Path:}] Devices:[] Seccomp:0xc0000cfc80 RootfsPropagation: MaskedPaths:[/proc/asound /proc/acpi /proc/kcore /proc/keys /proc/latency_stats /proc/timer_list /proc/timer_stats /proc/sched_debug /proc/scsi /sys/firmware] ReadonlyPaths:[/proc/bus /proc/fs /proc/irq /proc/sys /proc/sysrq-trigger] MountLabel: IntelRdt:<nil>}
D0613 06:29:18.234242       1 x:0] Spec.Linux.Resources.Memory: &{Limit:<nil> Reservation:<nil> Swap:<nil> Kernel:<nil> KernelTCP:<nil> Swappiness:<nil> DisableOOMKiller:0xc0001ece56}
D0613 06:29:18.234253       1 x:0] Spec.Linux.Resources.CPU: &{Shares:0xc0001ece58 Quota:<nil> Period:<nil> RealtimeRuntime:<nil> RealtimePeriod:<nil> Cpus: Mems:}
D0613 06:29:18.234266       1 x:0] Spec.Linux.Resources.BlockIO: &{Weight:0xc0001ece68 LeafWeight:<nil> WeightDevice:[] ThrottleReadBpsDevice:[] ThrottleWriteBpsDevice:[] ThrottleReadIOPSDevice:[] ThrottleWriteIOPSDevice:[]}
D0613 06:29:18.234278       1 x:0] Spec.Linux.Resources.Network: <nil>
D0613 06:29:18.234284       1 x:0] Spec.Process: &{Terminal:false ConsoleSize:<nil> User:{UID:0 GID:0 AdditionalGids:[] Username:} Args:[/hello] Env:[PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin HOSTNAME=8496793d4d0a] Cwd:/ Capabilities:0xc000174280 Rlimits:[] NoNewPrivileges:false ApparmorProfile:docker-default OOMScoreAdj:0xc0001ecb90 SelinuxLabel:}
D0613 06:29:18.234302       1 x:0] Spec.Root: &{Path:/var/lib/docker/overlay2/aa0bfb5ad159f36c0a3d28e0e569ef1f8c5a3980dcc5ffc7fa8f8c1399147a15/merged Readonly:false}
D0613 06:29:18.234311       1 x:0] Spec.Mounts: [{Destination:/proc Type:proc Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/proc Options:[nosuid noexec nodev]} {Destination:/dev Type:tmpfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/tmpfs Options:[nosuid strictatime mode=755 size=65536k]} {Destination:/dev/pts Type:devpts Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/devpts Options:[nosuid noexec newinstance ptmxmode=0666 mode=0620 gid=5]} {Destination:/sys Type:sysfs Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/sysfs Options:[nosuid noexec nodev ro]} {Destination:/sys/fs/cgroup Type:cgroup Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/cgroup Options:[ro nosuid noexec nodev]} {Destination:/dev/mqueue Type:mqueue Source:/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mqueue Options:[nosuid noexec nodev]} {Destination:/etc/resolv.conf Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/resolv.conf Options:[rbind rprivate]} {Destination:/etc/hostname Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hostname Options:[rbind rprivate]} {Destination:/etc/hosts Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hosts Options:[rbind rprivate]} {Destination:/dev/shm Type:bind Source:/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/mounts/shm Options:[rbind rprivate]}]
I0613 06:29:18.234415       1 x:0] Platform: kvm
I0613 06:29:18.234467       1 x:0] excluded: virtual [7ffdd6ddc000,7ffdd6ddf000)
I0613 06:29:18.234478       1 x:0] excluded: virtual [7ffdd6ddf000,7ffdd6de1000)
I0613 06:29:18.234505       1 x:0] region: virtual [bdc00000,407e800000)
I0613 06:29:18.234512       1 x:0] region: virtual [407e800000,c000000000)
I0613 06:29:18.234521       1 x:0] region: virtual [c6d1065000,10691c65000)
I0613 06:29:18.234527       1 x:0] region: virtual [10691c65000,18613465000)
I0613 06:29:18.234535       1 x:0] region: virtual [18613465000,38419465000)
I0613 06:29:18.234541       1 x:0] region: virtual [38419465000,78025465000)
I0613 06:29:18.234546       1 x:0] region: virtual [78025465000,f783d465000)
I0613 06:29:18.234551       1 x:0] region: virtual [f783d465000,1f686d465000)
I0613 06:29:18.234557       1 x:0] region: virtual [1f686d465000,3f48cd464000)
I0613 06:29:18.234562       1 x:0] region: virtual [3f48cd464000,7f098d462000)
I0613 06:29:18.234568       1 x:0] region: virtual [7f098f874000,7f8911074000)
I0613 06:29:18.234573       1 x:0] region: virtual [7f8911074000,7fc8d1c74000)
I0613 06:29:18.234580       1 x:0] region: virtual [7ffdd6ddc000,7ffdd6ddf000)
I0613 06:29:18.234587       1 x:0] region: virtual [7ffdd6ddf000,7ffdd6de1000)
I0613 06:29:18.234593       1 x:0] physicalRegion: virtual [1000,bdc00000) => physical [100001000,1bdc00000)
I0613 06:29:18.234600       1 x:0] physicalRegion: virtual [c000000000,c6d1065000) => physical [200000000,8d1065000)
I0613 06:29:18.234607       1 x:0] physicalRegion: virtual [7f098d462000,7f098f874000) => physical [90d462000,90f874000)
I0613 06:29:18.234615       1 x:0] physicalRegion: virtual [7fc8d1c74000,7ffdd6ddc000) => physical [951c74000,3e56ddc000)
I0613 06:29:18.234621       1 x:0] physicalRegion: virtual [7ffdd6de1000,7ffffffff000) => physical [3e56de1000,407ffff000)
D0613 06:29:18.234954       1 x:0] The maximum number of vCPUs is 288.
I0613 06:29:18.243684       1 x:0] CPUs: 4
I0613 06:29:18.243726       1 x:0] Packet logging disabled
D0613 06:29:18.244463       1 x:0] Time: Adjusting syscall overhead up to 2000
D0613 06:29:18.244544       1 x:0] Time: Adjusting syscall overhead up to 2000
D0613 06:29:18.244552       1 x:0] Updating VDSO parameters: {monotonicReady:0 monotonicBaseCycles:0 monotonicBaseRef:0 monotonicFrequency:0 realtimeReady:0 realtimeBaseCycles:0 realtimeBaseRef:0 realtimeFrequency:0}
D0613 06:29:18.446892       1 x:0] urpc: unmarshal success.
I0613 06:29:18.447355       1 x:0] Enabling loopback interface "lo" with id 1 on addresses [127.0.0.1]
I0613 06:29:18.447489       1 x:0] Enabling interface "eth0" with id 2 on addresses [172.17.0.2] (02:42:ac:11:00:02)
I0613 06:29:18.447652       1 x:0] Setting routes [{Destination:127.0.0.0 Mask:255.0.0.0 Gateway: NIC:1} {Destination:172.17.0.0 Mask:255.255.0.0 Gateway: NIC:2} {Destination:0.0.0.0 Mask:0.0.0.0 Gateway:172.17.0.1 NIC:2}]
D0613 06:29:18.447846       1 x:0] urpc: successfully marshalled 37 bytes.
D0613 06:29:18.448256       1 x:0] urpc: unmarshal success.
D0613 06:29:18.448343       1 x:0] containerManager.StartRoot "8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222"
I0613 06:29:18.448474       1 x:0] Installing seccomp filters for 55 syscalls (action=kill process)
D0613 06:29:18.448629       1 x:0] syscall filter read: [] => 0x616c6c6f77
D0613 06:29:18.448669       1 x:0] syscall filter write: [] => 0x616c6c6f77
D0613 06:29:18.448698       1 x:0] syscall filter close: [] => 0x616c6c6f77
D0613 06:29:18.448732       1 x:0] syscall filter fstat: [] => 0x616c6c6f77
D0613 06:29:18.448760       1 x:0] syscall filter poll: [] => 0x616c6c6f77
D0613 06:29:18.448787       1 x:0] syscall filter lseek: [] => 0x616c6c6f77
D0613 06:29:18.448813       1 x:0] syscall filter mmap: [( * * * 0x1  ) ( * * * 0x2  ) ( * * * 0x22  ) ( * * * 0x20022  ) ( * * * 0x4022  ) ( * * 0x3  0x32  ) ( )] => 0x616c6c6f77
D0613 06:29:18.448964       1 x:0] syscall filter mprotect: [] => 0x616c6c6f77
D0613 06:29:18.448999       1 x:0] syscall filter munmap: [] => 0x616c6c6f77
D0613 06:29:18.449028       1 x:0] syscall filter rt_sigaction: [] => 0x616c6c6f77
D0613 06:29:18.449099       1 x:0] syscall filter rt_sigprocmask: [] => 0x616c6c6f77
D0613 06:29:18.449139       1 x:0] syscall filter rt_sigreturn: [] => 0x616c6c6f77
D0613 06:29:18.449167       1 x:0] syscall filter ioctl: [( * 0x5401  * ) ( * 0x5402  * ) ( * 0x5404  * ) ( * 0x5403  * ) ( * 0x5414  * ) ( * 0x5413  * ) ( )] => 0x616c6c6f77
D0613 06:29:18.449269       1 x:0] syscall filter pread64: [] => 0x616c6c6f77
D0613 06:29:18.449306       1 x:0] syscall filter pwrite64: [] => 0x616c6c6f77
D0613 06:29:18.449335       1 x:0] syscall filter writev: [( * * 0x2  ) ( * * 0x3  )] => 0x616c6c6f77
D0613 06:29:18.449395       1 x:0] syscall filter sched_yield: [] => 0x616c6c6f77
D0613 06:29:18.449424       1 x:0] syscall filter mincore: [] => 0x616c6c6f77
D0613 06:29:18.449452       1 x:0] syscall filter madvise: [] => 0x616c6c6f77
D0613 06:29:18.449480       1 x:0] syscall filter dup: [] => 0x616c6c6f77
D0613 06:29:18.449507       1 x:0] syscall filter nanosleep: [] => 0x616c6c6f77
D0613 06:29:18.449543       1 x:0] syscall filter setitimer: [] => 0x616c6c6f77
D0613 06:29:18.449570       1 x:0] syscall filter getpid: [] => 0x616c6c6f77
D0613 06:29:18.449597       1 x:0] syscall filter accept: [( 0x5  )] => 0x616c6c6f77
D0613 06:29:18.449642       1 x:0] syscall filter sendmsg: [( * * 0x4040  )] => 0x616c6c6f77
D0613 06:29:18.449683       1 x:0] syscall filter recvmsg: [( * * 0x60  ) ( * * 0x62  )] => 0x616c6c6f77
D0613 06:29:18.449743       1 x:0] syscall filter shutdown: [( * 0x2  )] => 0x616c6c6f77
D0613 06:29:18.449782       1 x:0] syscall filter listen: [( 0x5  0x10  )] => 0x616c6c6f77
D0613 06:29:18.449826       1 x:0] syscall filter getsockopt: [( * 0x1  0x27  ) ( * 0x1  0x3  ) ( * 0x1  0x4  ) ( * 0x1  0x7  ) ( * 0x1  0x2  ) ( * 0x1  0x11  )] => 0x616c6c6f77
D0613 06:29:18.449923       1 x:0] syscall filter clone: [( 0x50f00  )] => 0x616c6c6f77
D0613 06:29:18.449959       1 x:0] syscall filter exit: [] => 0x616c6c6f77
D0613 06:29:18.449986       1 x:0] syscall filter fcntl: [( * 0x3  ) ( * 0x4  ) ( * 0x1  )] => 0x616c6c6f77
D0613 06:29:18.450049       1 x:0] syscall filter fsync: [] => 0x616c6c6f77
D0613 06:29:18.450077       1 x:0] syscall filter ftruncate: [] => 0x616c6c6f77
D0613 06:29:18.450104       1 x:0] syscall filter fchmod: [] => 0x616c6c6f77
D0613 06:29:18.450131       1 x:0] syscall filter gettimeofday: [] => 0x616c6c6f77
D0613 06:29:18.450173       1 x:0] syscall filter rt_sigtimedwait: [] => 0x616c6c6f77
D0613 06:29:18.450202       1 x:0] syscall filter rt_sigsuspend: [] => 0x616c6c6f77
D0613 06:29:18.450230       1 x:0] syscall filter sigaltstack: [] => 0x616c6c6f77
D0613 06:29:18.450257       1 x:0] syscall filter arch_prctl: [( 0x1003  ) ( 0x1002  ) ( )] => 0x616c6c6f77
D0613 06:29:18.450309       1 x:0] syscall filter gettid: [] => 0x616c6c6f77
D0613 06:29:18.450336       1 x:0] syscall filter futex: [( * 0x80  * * 0x0  ) ( * 0x81  * * 0x0  )] => 0x616c6c6f77
D0613 06:29:18.450407       1 x:0] syscall filter restart_syscall: [] => 0x616c6c6f77
D0613 06:29:18.450440       1 x:0] syscall filter clock_gettime: [] => 0x616c6c6f77
D0613 06:29:18.450468       1 x:0] syscall filter exit_group: [] => 0x616c6c6f77
D0613 06:29:18.450495       1 x:0] syscall filter epoll_ctl: [] => 0x616c6c6f77
D0613 06:29:18.450522       1 x:0] syscall filter tgkill: [( 0x1  )] => 0x616c6c6f77
D0613 06:29:18.450556       1 x:0] syscall filter sync_file_range: [] => 0x616c6c6f77
D0613 06:29:18.450584       1 x:0] syscall filter epoll_pwait: [( * * * * 0x0  )] => 0x616c6c6f77
D0613 06:29:18.450628       1 x:0] syscall filter fallocate: [] => 0x616c6c6f77
D0613 06:29:18.450662       1 x:0] syscall filter eventfd2: [( 0x0  0x0  )] => 0x616c6c6f77
D0613 06:29:18.450700       1 x:0] syscall filter epoll_create1: [] => 0x616c6c6f77
D0613 06:29:18.450727       1 x:0] syscall filter recvmmsg: [( * * 0x8  0x40  0x0  )] => 0x616c6c6f77
D0613 06:29:18.450775       1 x:0] syscall filter sys_318: [] => 0x616c6c6f77
D0613 06:29:18.450804       1 x:0] syscall filter sys_18446744073709551615: [] => 0x616c6c6f77
D0613 06:29:18.452604       1 x:0] Seccomp program dump:
0: A <- P[4:4]
1: pc += (A == 3221225534) ? 1 [3] : 0 [2]
2: pc += 473 [476]
3: A <- P[0:4]
4: pc += (A == 50) ? 3 [8] : 0 [5]
5: pc += (A > 50) ? 0 [6] : 1 [7]
6: pc += 223 [230]
7: pc += 10 [18]
8: A <- P[16:4]
9: pc += (A == 5) ? 0 [10] : 7 [17]
10: A <- P[20:4]
11: pc += (A == 0) ? 0 [12] : 5 [17]
12: A <- P[24:4]
13: pc += (A == 16) ? 0 [14] : 3 [17]
14: A <- P[28:4]
15: pc += (A == 0) ? 0 [16] : 1 [17]
16: ret 2147418112
17: pc += 458 [476]
18: pc += (A == 17) ? 3 [22] : 0 [19]
19: pc += (A > 17) ? 0 [20] : 1 [21]
20: pc += 121 [142]
21: pc += 1 [23]
22: ret 2147418112
23: pc += (A == 9) ? 3 [27] : 0 [24]
24: pc += (A > 9) ? 0 [25] : 1 [26]
25: pc += 61 [87]
26: pc += 36 [63]
27: A <- P[40:4]
28: pc += (A == 1) ? 0 [29] : 3 [32]
29: A <- P[44:4]
30: pc += (A == 0) ? 0 [31] : 1 [32]
31: ret 2147418112
32: A <- P[40:4]
33: pc += (A == 2) ? 0 [34] : 3 [37]
34: A <- P[44:4]
35: pc += (A == 0) ? 0 [36] : 1 [37]
36: ret 2147418112
37: A <- P[40:4]
38: pc += (A == 34) ? 0 [39] : 3 [42]
39: A <- P[44:4]
40: pc += (A == 0) ? 0 [41] : 1 [42]
41: ret 2147418112
42: A <- P[40:4]
43: pc += (A == 131106) ? 0 [44] : 3 [47]
44: A <- P[44:4]
45: pc += (A == 0) ? 0 [46] : 1 [47]
46: ret 2147418112
47: A <- P[40:4]
48: pc += (A == 16418) ? 0 [49] : 3 [52]
49: A <- P[44:4]
50: pc += (A == 0) ? 0 [51] : 1 [52]
51: ret 2147418112
52: A <- P[32:4]
53: pc += (A == 3) ? 0 [54] : 7 [61]
54: A <- P[36:4]
55: pc += (A == 0) ? 0 [56] : 5 [61]
56: A <- P[40:4]
57: pc += (A == 50) ? 0 [58] : 3 [61]
58: A <- P[44:4]
59: pc += (A == 0) ? 0 [60] : 1 [61]
60: ret 2147418112
61: ret 2147418112
62: pc += 413 [476]
63: pc += (A == 5) ? 3 [67] : 0 [64]
64: pc += (A > 5) ? 0 [65] : 1 [66]
65: pc += 13 [79]
66: pc += 1 [68]
67: ret 2147418112
68: pc += (A == 1) ? 3 [72] : 0 [69]
69: pc += (A > 1) ? 0 [70] : 1 [71]
70: pc += 5 [76]
71: pc += 1 [73]
72: ret 2147418112
73: pc += (A == 0) ? 1 [75] : 0 [74]
74: pc += 401 [476]
75: ret 2147418112
76: pc += (A == 3) ? 1 [78] : 0 [77]
77: pc += 398 [476]
78: ret 2147418112
79: pc += (A == 8) ? 3 [83] : 0 [80]
80: pc += (A > 8) ? 0 [81] : 1 [82]
81: pc += 394 [476]
82: pc += 1 [84]
83: ret 2147418112
84: pc += (A == 7) ? 1 [86] : 0 [85]
85: pc += 390 [476]
86: ret 2147418112
87: pc += (A == 14) ? 3 [91] : 0 [88]
88: pc += (A > 14) ? 0 [89] : 1 [90]
89: pc += 13 [103]
90: pc += 1 [92]
91: ret 2147418112
92: pc += (A == 11) ? 3 [96] : 0 [93]
93: pc += (A > 11) ? 0 [94] : 1 [95]
94: pc += 5 [100]
95: pc += 1 [97]
96: ret 2147418112
97: pc += (A == 10) ? 1 [99] : 0 [98]
98: pc += 377 [476]
99: ret 2147418112
100: pc += (A == 13) ? 1 [102] : 0 [101]
101: pc += 374 [476]
102: ret 2147418112
103: pc += (A == 16) ? 3 [107] : 0 [104]
104: pc += (A > 16) ? 0 [105] : 1 [106]
105: pc += 370 [476]
106: pc += 32 [139]
107: A <- P[24:4]
108: pc += (A == 21505) ? 0 [109] : 3 [112]
109: A <- P[28:4]
110: pc += (A == 0) ? 0 [111] : 1 [112]
111: ret 2147418112
112: A <- P[24:4]
113: pc += (A == 21506) ? 0 [114] : 3 [117]
114: A <- P[28:4]
115: pc += (A == 0) ? 0 [116] : 1 [117]
116: ret 2147418112
117: A <- P[24:4]
118: pc += (A == 21508) ? 0 [119] : 3 [122]
119: A <- P[28:4]
120: pc += (A == 0) ? 0 [121] : 1 [122]
121: ret 2147418112
122: A <- P[24:4]
123: pc += (A == 21507) ? 0 [124] : 3 [127]
124: A <- P[28:4]
125: pc += (A == 0) ? 0 [126] : 1 [127]
126: ret 2147418112
127: A <- P[24:4]
128: pc += (A == 21524) ? 0 [129] : 3 [132]
129: A <- P[28:4]
130: pc += (A == 0) ? 0 [131] : 1 [132]
131: ret 2147418112
132: A <- P[24:4]
133: pc += (A == 21523) ? 0 [134] : 3 [137]
134: A <- P[28:4]
135: pc += (A == 0) ? 0 [136] : 1 [137]
136: ret 2147418112
137: ret 2147418112
138: pc += 337 [476]
139: pc += (A == 15) ? 1 [141] : 0 [140]
140: pc += 335 [476]
141: ret 2147418112
142: pc += (A == 35) ? 3 [146] : 0 [143]
143: pc += (A > 35) ? 0 [144] : 1 [145]
144: pc += 36 [181]
145: pc += 1 [147]
146: ret 2147418112
147: pc += (A == 27) ? 3 [151] : 0 [148]
148: pc += (A > 27) ? 0 [149] : 1 [150]
149: pc += 23 [173]
150: pc += 1 [152]
151: ret 2147418112
152: pc += (A == 20) ? 3 [156] : 0 [153]
153: pc += (A > 20) ? 0 [154] : 1 [155]
154: pc += 15 [170]
155: pc += 11 [167]
156: A <- P[32:4]
157: pc += (A == 2) ? 0 [158] : 3 [161]
158: A <- P[36:4]
159: pc += (A == 0) ? 0 [160] : 1 [161]
160: ret 2147418112
161: A <- P[32:4]
162: pc += (A == 3) ? 0 [163] : 3 [166]
163: A <- P[36:4]
164: pc += (A == 0) ? 0 [165] : 1 [166]
165: ret 2147418112
166: pc += 309 [476]
167: pc += (A == 18) ? 1 [169] : 0 [168]
168: pc += 307 [476]
169: ret 2147418112
170: pc += (A == 24) ? 1 [172] : 0 [171]
171: pc += 304 [476]
172: ret 2147418112
173: pc += (A == 32) ? 3 [177] : 0 [174]
174: pc += (A > 32) ? 0 [175] : 1 [176]
175: pc += 300 [476]
176: pc += 1 [178]
177: ret 2147418112
178: pc += (A == 28) ? 1 [180] : 0 [179]
179: pc += 296 [476]
180: ret 2147418112
181: pc += (A == 46) ? 3 [185] : 0 [182]
182: pc += (A > 46) ? 0 [183] : 1 [184]
183: pc += 23 [207]
184: pc += 6 [191]
185: A <- P[32:4]
186: pc += (A == 16448) ? 0 [187] : 3 [190]
187: A <- P[36:4]
188: pc += (A == 0) ? 0 [189] : 1 [190]
189: ret 2147418112
190: pc += 285 [476]
191: pc += (A == 39) ? 3 [195] : 0 [192]
192: pc += (A > 39) ? 0 [193] : 1 [194]
193: pc += 5 [199]
194: pc += 1 [196]
195: ret 2147418112
196: pc += (A == 38) ? 1 [198] : 0 [197]
197: pc += 278 [476]
198: ret 2147418112
199: pc += (A == 43) ? 1 [201] : 0 [200]
200: pc += 275 [476]
201: A <- P[16:4]
202: pc += (A == 5) ? 0 [203] : 3 [206]
203: A <- P[20:4]
204: pc += (A == 0) ? 0 [205] : 1 [206]
205: ret 2147418112
206: pc += 269 [476]
207: pc += (A == 48) ? 3 [211] : 0 [208]
208: pc += (A > 48) ? 0 [209] : 1 [210]
209: pc += 266 [476]
210: pc += 6 [217]
211: A <- P[24:4]
212: pc += (A == 2) ? 0 [213] : 3 [216]
213: A <- P[28:4]
214: pc += (A == 0) ? 0 [215] : 1 [216]
215: ret 2147418112
216: pc += 259 [476]
217: pc += (A == 47) ? 1 [219] : 0 [218]
218: pc += 257 [476]
219: A <- P[32:4]
220: pc += (A == 96) ? 0 [221] : 3 [224]
221: A <- P[36:4]
222: pc += (A == 0) ? 0 [223] : 1 [224]
223: ret 2147418112
224: A <- P[32:4]
225: pc += (A == 98) ? 0 [226] : 3 [229]
226: A <- P[36:4]
227: pc += (A == 0) ? 0 [228] : 1 [229]
228: ret 2147418112
229: pc += 246 [476]
230: pc += (A == 202) ? 3 [234] : 0 [231]
231: pc += (A > 202) ? 0 [232] : 1 [233]
232: pc += 158 [391]
233: pc += 19 [253]
234: A <- P[24:4]
235: pc += (A == 128) ? 0 [236] : 7 [243]
236: A <- P[28:4]
237: pc += (A == 0) ? 0 [238] : 5 [243]
238: A <- P[48:4]
239: pc += (A == 0) ? 0 [240] : 3 [243]
240: A <- P[52:4]
241: pc += (A == 0) ? 0 [242] : 1 [243]
242: ret 2147418112
243: A <- P[24:4]
244: pc += (A == 129) ? 0 [245] : 7 [252]
245: A <- P[28:4]
246: pc += (A == 0) ? 0 [247] : 5 [252]
247: A <- P[48:4]
248: pc += (A == 0) ? 0 [249] : 3 [252]
249: A <- P[52:4]
250: pc += (A == 0) ? 0 [251] : 1 [252]
251: ret 2147418112
252: pc += 223 [476]
253: pc += (A == 91) ? 3 [257] : 0 [254]
254: pc += (A > 91) ? 0 [255] : 1 [256]
255: pc += 100 [356]
256: pc += 1 [258]
257: ret 2147418112
258: pc += (A == 72) ? 3 [262] : 0 [259]
259: pc += (A > 72) ? 0 [260] : 1 [261]
260: pc += 87 [348]
261: pc += 16 [278]
262: A <- P[24:4]
263: pc += (A == 3) ? 0 [264] : 3 [267]
264: A <- P[28:4]
265: pc += (A == 0) ? 0 [266] : 1 [267]
266: ret 2147418112
267: A <- P[24:4]
268: pc += (A == 4) ? 0 [269] : 3 [272]
269: A <- P[28:4]
270: pc += (A == 0) ? 0 [271] : 1 [272]
271: ret 2147418112
272: A <- P[24:4]
273: pc += (A == 1) ? 0 [274] : 3 [277]
274: A <- P[28:4]
275: pc += (A == 0) ? 0 [276] : 1 [277]
276: ret 2147418112
277: pc += 198 [476]
278: pc += (A == 56) ? 3 [282] : 0 [279]
279: pc += (A > 56) ? 0 [280] : 1 [281]
280: pc += 64 [345]
281: pc += 6 [288]
282: A <- P[16:4]
283: pc += (A == 331520) ? 0 [284] : 3 [287]
284: A <- P[20:4]
285: pc += (A == 0) ? 0 [286] : 1 [287]
286: ret 2147418112
287: pc += 188 [476]
288: pc += (A == 55) ? 1 [290] : 0 [289]
289: pc += 186 [476]
290: A <- P[24:4]
291: pc += (A == 1) ? 0 [292] : 7 [299]
292: A <- P[28:4]
293: pc += (A == 0) ? 0 [294] : 5 [299]
294: A <- P[32:4]
295: pc += (A == 39) ? 0 [296] : 3 [299]
296: A <- P[36:4]
297: pc += (A == 0) ? 0 [298] : 1 [299]
298: ret 2147418112
299: A <- P[24:4]
300: pc += (A == 1) ? 0 [301] : 7 [308]
301: A <- P[28:4]
302: pc += (A == 0) ? 0 [303] : 5 [308]
303: A <- P[32:4]
304: pc += (A == 3) ? 0 [305] : 3 [308]
305: A <- P[36:4]
306: pc += (A == 0) ? 0 [307] : 1 [308]
307: ret 2147418112
308: A <- P[24:4]
309: pc += (A == 1) ? 0 [310] : 7 [317]
310: A <- P[28:4]
311: pc += (A == 0) ? 0 [312] : 5 [317]
312: A <- P[32:4]
313: pc += (A == 4) ? 0 [314] : 3 [317]
314: A <- P[36:4]
315: pc += (A == 0) ? 0 [316] : 1 [317]
316: ret 2147418112
317: A <- P[24:4]
318: pc += (A == 1) ? 0 [319] : 7 [326]
319: A <- P[28:4]
320: pc += (A == 0) ? 0 [321] : 5 [326]
321: A <- P[32:4]
322: pc += (A == 7) ? 0 [323] : 3 [326]
323: A <- P[36:4]
324: pc += (A == 0) ? 0 [325] : 1 [326]
325: ret 2147418112
326: A <- P[24:4]
327: pc += (A == 1) ? 0 [328] : 7 [335]
328: A <- P[28:4]
329: pc += (A == 0) ? 0 [330] : 5 [335]
330: A <- P[32:4]
331: pc += (A == 2) ? 0 [332] : 3 [335]
332: A <- P[36:4]
333: pc += (A == 0) ? 0 [334] : 1 [335]
334: ret 2147418112
335: A <- P[24:4]
336: pc += (A == 1) ? 0 [337] : 7 [344]
337: A <- P[28:4]
338: pc += (A == 0) ? 0 [339] : 5 [344]
339: A <- P[32:4]
340: pc += (A == 17) ? 0 [341] : 3 [344]
341: A <- P[36:4]
342: pc += (A == 0) ? 0 [343] : 1 [344]
343: ret 2147418112
344: pc += 131 [476]
345: pc += (A == 60) ? 1 [347] : 0 [346]
346: pc += 129 [476]
347: ret 2147418112
348: pc += (A == 77) ? 3 [352] : 0 [349]
349: pc += (A > 77) ? 0 [350] : 1 [351]
350: pc += 125 [476]
351: pc += 1 [353]
352: ret 2147418112
353: pc += (A == 74) ? 1 [355] : 0 [354]
354: pc += 121 [476]
355: ret 2147418112
356: pc += (A == 131) ? 3 [360] : 0 [357]
357: pc += (A > 131) ? 0 [358] : 1 [359]
358: pc += 13 [372]
359: pc += 1 [361]
360: ret 2147418112
361: pc += (A == 128) ? 3 [365] : 0 [362]
362: pc += (A > 128) ? 0 [363] : 1 [364]
363: pc += 5 [369]
364: pc += 1 [366]
365: ret 2147418112
366: pc += (A == 96) ? 1 [368] : 0 [367]
367: pc += 108 [476]
368: ret 2147418112
369: pc += (A == 130) ? 1 [371] : 0 [370]
370: pc += 105 [476]
371: ret 2147418112
372: pc += (A == 186) ? 3 [376] : 0 [373]
373: pc += (A > 186) ? 0 [374] : 1 [375]
374: pc += 101 [476]
375: pc += 1 [377]
376: ret 2147418112
377: pc += (A == 158) ? 1 [379] : 0 [378]
378: pc += 97 [476]
379: A <- P[16:4]
380: pc += (A == 4099) ? 0 [381] : 3 [384]
381: A <- P[20:4]
382: pc += (A == 0) ? 0 [383] : 1 [384]
383: ret 2147418112
384: A <- P[16:4]
385: pc += (A == 4098) ? 0 [386] : 3 [389]
386: A <- P[20:4]
387: pc += (A == 0) ? 0 [388] : 1 [389]
388: ret 2147418112
389: ret 2147418112
390: pc += 85 [476]
391: pc += (A == 281) ? 3 [395] : 0 [392]
392: pc += (A > 281) ? 0 [393] : 1 [394]
393: pc += 36 [430]
394: pc += 6 [401]
395: A <- P[48:4]
396: pc += (A == 0) ? 0 [397] : 3 [400]
397: A <- P[52:4]
398: pc += (A == 0) ? 0 [399] : 1 [400]
399: ret 2147418112
400: pc += 75 [476]
401: pc += (A == 233) ? 3 [405] : 0 [402]
402: pc += (A > 233) ? 0 [403] : 1 [404]
403: pc += 13 [417]
404: pc += 1 [406]
405: ret 2147418112
406: pc += (A == 228) ? 3 [410] : 0 [407]
407: pc += (A > 228) ? 0 [408] : 1 [409]
408: pc += 5 [414]
409: pc += 1 [411]
410: ret 2147418112
411: pc += (A == 219) ? 1 [413] : 0 [412]
412: pc += 63 [476]
413: ret 2147418112
414: pc += (A == 231) ? 1 [416] : 0 [415]
415: pc += 60 [476]
416: ret 2147418112
417: pc += (A == 277) ? 3 [421] : 0 [418]
418: pc += (A > 277) ? 0 [419] : 1 [420]
419: pc += 56 [476]
420: pc += 1 [422]
421: ret 2147418112
422: pc += (A == 234) ? 1 [424] : 0 [423]
423: pc += 52 [476]
424: A <- P[16:4]
425: pc += (A == 1) ? 0 [426] : 3 [429]
426: A <- P[20:4]
427: pc += (A == 0) ? 0 [428] : 1 [429]
428: ret 2147418112
429: pc += 46 [476]
430: pc += (A == 299) ? 3 [434] : 0 [431]
431: pc += (A > 299) ? 0 [432] : 1 [433]
432: pc += 35 [468]
433: pc += 14 [448]
434: A <- P[32:4]
435: pc += (A == 8) ? 0 [436] : 11 [447]
436: A <- P[36:4]
437: pc += (A == 0) ? 0 [438] : 9 [447]
438: A <- P[40:4]
439: pc += (A == 64) ? 0 [440] : 7 [447]
440: A <- P[44:4]
441: pc += (A == 0) ? 0 [442] : 5 [447]
442: A <- P[48:4]
443: pc += (A == 0) ? 0 [444] : 3 [447]
444: A <- P[52:4]
445: pc += (A == 0) ? 0 [446] : 1 [447]
446: ret 2147418112
447: pc += 28 [476]
448: pc += (A == 290) ? 3 [452] : 0 [449]
449: pc += (A > 290) ? 0 [450] : 1 [451]
450: pc += 14 [465]
451: pc += 10 [462]
452: A <- P[16:4]
453: pc += (A == 0) ? 0 [454] : 7 [461]
454: A <- P[20:4]
455: pc += (A == 0) ? 0 [456] : 5 [461]
456: A <- P[24:4]
457: pc += (A == 0) ? 0 [458] : 3 [461]
458: A <- P[28:4]
459: pc += (A == 0) ? 0 [460] : 1 [461]
460: ret 2147418112
461: pc += 14 [476]
462: pc += (A == 285) ? 1 [464] : 0 [463]
463: pc += 12 [476]
464: ret 2147418112
465: pc += (A == 291) ? 1 [467] : 0 [466]
466: pc += 9 [476]
467: ret 2147418112
468: pc += (A == 4294967295) ? 3 [472] : 0 [469]
469: pc += (A > 4294967295) ? 0 [470] : 1 [471]
470: pc += 5 [476]
471: pc += 1 [473]
472: ret 2147418112
473: pc += (A == 318) ? 1 [475] : 0 [474]
474: pc += 1 [476]
475: ret 2147418112
476: ret 2147483648

I0613 06:29:18.453670       1 x:0] Seccomp filters installed.
W0613 06:29:18.453902       1 x:0] ignoring dev mount at "/dev"
W0613 06:29:18.453946       1 x:0] ignoring dev mount at "/dev/pts"
W0613 06:29:18.453991       1 x:0] ignoring dev mount at "/dev/shm"
I0613 06:29:18.454032       1 x:0] Mounting root over 9P, ioFD: 9
D0613 06:29:18.454142       1 x:0] send [FD 9] [Tag 000001] Tversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.454769       1 x:0] recv [FD 9] [Tag 000001] Rversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.454838       1 x:0] send [FD 9] [Tag 000001] Tattach{FID: 1, AuthFID: 4294967295, UserName: , AttachName: /, UID: 4294967295}
D0613 06:29:18.455546       1 x:0] recv [FD 9] [Tag 000001] Rattach{QID: QID{Type: 128, Version: 0, Path: 9702100}}
D0613 06:29:18.455655       1 x:0] send [FD 9] [Tag 000001] Tgetattr{FID: 1, AttrMask: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime INo Size Blocks BTime Gen DataVersion}}
D0613 06:29:18.456277       1 x:0] recv [FD 9] [Tag 000001] Rgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, QID: QID{Type: 128, Version: 0, Path: 9702100}, Attr: Attr{Mode: 0o40755, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 4096, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 28036880}, MTime: {Sec: 1560407357, NanoSec: 28036880}, CTime: {Sec: 1560407357, NanoSec: 28036880}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}}
I0613 06:29:18.456644       1 x:0] Mounted "/var/lib/docker/overlay2/aa0bfb5ad159f36c0a3d28e0e569ef1f8c5a3980dcc5ffc7fa8f8c1399147a15/merged" to "/" type root
I0613 06:29:18.456834       1 x:0] Adding submount overlay over "/dev"
D0613 06:29:18.456930       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 2, Names: [dev]}
D0613 06:29:18.457608       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o40755, UID: 0, GID: 0, NLink: 4, RDev: 0, Size: 4096, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407356, NanoSec: 916041110}, MTime: {Sec: 1560407356, NanoSec: 916041110}, CTime: {Sec: 1560407356, NanoSec: 916041110}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 128, Version: 0, Path: 9702112}]}
I0613 06:29:18.457734       1 x:0] Mounted "" to "/dev" type devtmpfs
I0613 06:29:18.457854       1 x:0] Mounted "" to "/dev/pts" type devpts
W0613 06:29:18.457894       1 x:0] ignoring unknown mount option "nosuid"
W0613 06:29:18.457924       1 x:0] ignoring unknown mount option "nodev"
D0613 06:29:18.458892       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 3, Names: [proc]}
D0613 06:29:18.459593       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o40755, UID: 0, GID: 0, NLink: 2, RDev: 0, Size: 4096, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407356, NanoSec: 916041110}, MTime: {Sec: 1560407356, NanoSec: 916041110}, CTime: {Sec: 1560407356, NanoSec: 916041110}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 128, Version: 0, Path: 9702116}]}
I0613 06:29:18.459727       1 x:0] Mounted "/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/proc" to "/proc" type proc
W0613 06:29:18.459789       1 x:0] ignoring unknown mount option "nosuid"
W0613 06:29:18.459832       1 x:0] ignoring unknown mount option "nodev"
I0613 06:29:18.460060       1 x:0] Adding submount overlay over "/sys"
D0613 06:29:18.460148       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 4, Names: [sys]}
D0613 06:29:18.460800       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o40755, UID: 0, GID: 0, NLink: 2, RDev: 0, Size: 4096, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407356, NanoSec: 916041110}, MTime: {Sec: 1560407356, NanoSec: 916041110}, CTime: {Sec: 1560407356, NanoSec: 916041110}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 128, Version: 0, Path: 9702117}]}
I0613 06:29:18.460952       1 x:0] Mounted "/run/containerd/io.containerd.runtime.v1.linux/moby/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/sysfs" to "/sys" type sysfs
W0613 06:29:18.461013       1 x:0] ignoring unknown filesystem type "cgroup"
W0613 06:29:18.461044       1 x:0] ignoring unknown filesystem type "mqueue"
W0613 06:29:18.461117       1 x:0] ignoring unknown mount option "rbind"
W0613 06:29:18.461145       1 x:0] ignoring unknown mount option "rprivate"
D0613 06:29:18.461222       1 x:0] send [FD 10] [Tag 000001] Tversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.461609       1 x:0] recv [FD 10] [Tag 000001] Rversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.461701       1 x:0] send [FD 10] [Tag 000001] Tattach{FID: 1, AuthFID: 4294967295, UserName: , AttachName: /, UID: 4294967295}
D0613 06:29:18.462297       1 x:0] recv [FD 10] [Tag 000001] Rattach{QID: QID{Type: 0, Version: 0, Path: 8653607}}
D0613 06:29:18.462401       1 x:0] send [FD 10] [Tag 000001] Tgetattr{FID: 1, AttrMask: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime INo Size Blocks BTime Gen DataVersion}}
D0613 06:29:18.462972       1 x:0] recv [FD 10] [Tag 000001] Rgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, QID: QID{Type: 0, Version: 0, Path: 8653607}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 588, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 744009837}, MTime: {Sec: 1560407357, NanoSec: 744009837}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}}
D0613 06:29:18.463124       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 5, Names: [etc]}
D0613 06:29:18.463733       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o40755, UID: 0, GID: 0, NLink: 2, RDev: 0, Size: 4096, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407356, NanoSec: 916041110}, MTime: {Sec: 1560407356, NanoSec: 916041110}, CTime: {Sec: 1560407356, NanoSec: 916041110}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 128, Version: 0, Path: 9702107}]}
D0613 06:29:18.463862       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 5, NewFID: 6, Names: [resolv.conf]}
D0613 06:29:18.464452       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 588, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 744009837}, MTime: {Sec: 1560407357, NanoSec: 744009837}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 0, Version: 0, Path: 72057594046581543}]}
I0613 06:29:18.464571       1 x:0] Mounted "/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/resolv.conf" to "/etc/resolv.conf" type bind
W0613 06:29:18.464632       1 x:0] ignoring unknown mount option "rbind"
W0613 06:29:18.464661       1 x:0] ignoring unknown mount option "rprivate"
D0613 06:29:18.464731       1 x:0] send [FD 11] [Tag 000001] Tversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.465147       1 x:0] recv [FD 11] [Tag 000001] Rversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.465213       1 x:0] send [FD 11] [Tag 000001] Tattach{FID: 1, AuthFID: 4294967295, UserName: , AttachName: /, UID: 4294967295}
D0613 06:29:18.465775       1 x:0] recv [FD 11] [Tag 000001] Rattach{QID: QID{Type: 0, Version: 0, Path: 8653608}}
D0613 06:29:18.465880       1 x:0] send [FD 11] [Tag 000001] Tgetattr{FID: 1, AttrMask: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime INo Size Blocks BTime Gen DataVersion}}
D0613 06:29:18.466447       1 x:0] recv [FD 11] [Tag 000001] Rgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, QID: QID{Type: 0, Version: 0, Path: 8653608}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 13, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 880004701}, MTime: {Sec: 1560407357, NanoSec: 880004701}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}}
D0613 06:29:18.466567       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 5, NewFID: 7, Names: [hostname]}
D0613 06:29:18.467203       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 13, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 880004701}, MTime: {Sec: 1560407357, NanoSec: 880004701}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 0, Version: 0, Path: 72057594046581544}]}
I0613 06:29:18.467359       1 x:0] Mounted "/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hostname" to "/etc/hostname" type bind
W0613 06:29:18.467422       1 x:0] ignoring unknown mount option "rbind"
W0613 06:29:18.467452       1 x:0] ignoring unknown mount option "rprivate"
D0613 06:29:18.467523       1 x:0] send [FD 12] [Tag 000001] Tversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.467894       1 x:0] recv [FD 12] [Tag 000001] Rversion{MSize: 1048576, Version: 9P2000.L.Google.7}
D0613 06:29:18.468015       1 x:0] send [FD 12] [Tag 000001] Tattach{FID: 1, AuthFID: 4294967295, UserName: , AttachName: /, UID: 4294967295}
D0613 06:29:18.468480       1 x:0] recv [FD 12] [Tag 000001] Rattach{QID: QID{Type: 0, Version: 0, Path: 8653605}}
D0613 06:29:18.468573       1 x:0] send [FD 12] [Tag 000001] Tgetattr{FID: 1, AttrMask: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime INo Size Blocks BTime Gen DataVersion}}
D0613 06:29:18.469131       1 x:0] recv [FD 12] [Tag 000001] Rgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, QID: QID{Type: 0, Version: 0, Path: 8653605}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 174, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 744009837}, MTime: {Sec: 1560407357, NanoSec: 744009837}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}}
D0613 06:29:18.469255       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 5, NewFID: 8, Names: [hosts]}
D0613 06:29:18.469864       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o100644, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 174, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1560407357, NanoSec: 744009837}, MTime: {Sec: 1560407357, NanoSec: 744009837}, CTime: {Sec: 1560407357, NanoSec: 880004701}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 0, Version: 0, Path: 72057594046581541}]}
I0613 06:29:18.470002       1 x:0] Mounted "/var/lib/docker/containers/8496793d4d0af146de3e995691612a0f18676f3c62eeb8d63f83873ae2dfa222/hosts" to "/etc/hosts" type bind
D0613 06:29:18.470121       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 9, Names: [__runsc_containers__]}
D0613 06:29:18.470604       1 x:0] recv [FD 9] [Tag 000001] Rlerror{Error: 2}
I0613 06:29:18.470712       1 x:0] Mounted "" to "/__runsc_containers__" type tmpfs
D0613 06:29:18.470773       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 9, Names: [tmp]}
D0613 06:29:18.471210       1 x:0] recv [FD 9] [Tag 000001] Rlerror{Error: 2}
I0613 06:29:18.471358       1 x:0] Mounting internal tmpfs on top of empty "/tmp"
W0613 06:29:18.471424       1 x:0] ignoring unknown mount option "mode=1777"
I0613 06:29:18.471503       1 x:0] Mounted "" to "/tmp" type tmpfs
I0613 06:29:18.471556       1 x:0] EXEC: [/hello]
D0613 06:29:18.471691       1 x:0] send [FD 9] [Tag 000001] Twalkgetattr{FID: 1, NewFID: 9, Names: [hello]}
D0613 06:29:18.472340       1 x:0] recv [FD 9] [Tag 000001] Rwalkgetattr{Valid: AttrMask{with: Mode NLink UID GID RDev ATime MTime CTime Size Blocks}, Attr: Attr{Mode: 0o100775, UID: 0, GID: 0, NLink: 1, RDev: 0, Size: 1840, BlockSize: 4096, Blocks: 8, ATime: {Sec: 1546306076, NanoSec: 0}, MTime: {Sec: 1546306076, NanoSec: 0}, CTime: {Sec: 1559093511, NanoSec: 68004959}, BTime: {Sec: 0, NanoSec: 0}, Gen: 0, DataVersion: 0}, QIDs: [QID{Type: 0, Version: 0, Path: 7602844}]}
D0613 06:29:18.472487       1 x:0] send [FD 9] [Tag 000001] Twalk{FID: 9, NewFID: 10, Names: []}
D0613 06:29:18.472973       1 x:0] recv [FD 9] [Tag 000001] Rwalk{QIDs: []}
D0613 06:29:18.473100       1 x:0] send [FD 9] [Tag 000001] Tlopen{FID: 10, Flags: ReadOnly}
D0613 06:29:18.473662       1 x:0] recv [FD 9] [Tag 000001] Rlopen{QID: QID{Type: 0, Version: 0, Path: 7602844}, IoUnit: 0, File: &{{31}}}
D0613 06:29:18.474500       1 x:0] Allocating stack with size of 8388608 bytes
I0613 06:29:18.474788       1 x:0] Process should have started...
I0613 06:29:18.474835       1 x:0] Starting watchdog, period: 45s, timeout: 3m0s, action: LogWarning
D0613 06:29:18.475232       1 x:0] urpc: successfully marshalled 37 bytes.
D0613 06:29:18.476868       1 x:0] [   1] Interrupt queued
I0613 06:29:18.495098       1 x:0] [   1] hello E write(0x1 host:[3], 0x4001c0 "\nHello from Docker!\nThis message shows that your installation appears to be working correctly.\n\nTo generate this message, Docker took the following steps:\n 1. The Docker client contacted the Docker daemon.\n 2. The Docker daemon pulled the \"hello-world\" image from the Docker Hub.\n    (amd64)\n 3. The Docker daemon created a new container from that image which runs the\n    executable that produces the output you are currently reading.\n 4. The Docker daemon streamed that output to the Docker client, which sent it\n    to your terminal.\n\nTo try something more ambitious, you can run an Ubuntu container with:\n $ docker run -it ubuntu bash\n\nShare images, automate workflows, and more with a free Docker ID:\n https://hub.docker.com/\n\nFor more examples and ideas, visit:\n https://docs.docker.com/get-started/\n\n", 0x327)
I0613 06:29:18.495283       1 x:0] [   1] hello X write(0x1 host:[3], ..., 0x327) = 0x327 (43.491µs)
I0613 06:29:18.495410       1 x:0] [   1] hello E exit(0x0)
I0613 06:29:18.495458       1 x:0] [   1] hello X exit(0x0) = 0x0 (2.832µs)
D0613 06:29:18.495490       1 x:0] [   1] Transitioning from exit state TaskExitNone to TaskExitInitiated
D0613 06:29:18.496399       1 x:0] [   1] Init process terminating, killing namespace
D0613 06:29:18.496451       1 x:0] [   1] Transitioning from exit state TaskExitInitiated to TaskExitZombie
D0613 06:29:18.496486       1 x:0] [   1] Transitioning from exit state TaskExitZombie to TaskExitDead
I0613 06:29:18.496542       1 x:0] application exiting with {Code:0 Signo:0}
I0613 06:29:18.496718       1 x:0] Stopping watchdog
I0613 06:29:18.496794       1 x:0] Watchdog stopped
I0613 06:29:18.496837       1 x:0] Exiting with status: 0
