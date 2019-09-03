def get_syscall_dict():
    file_path = '/home/zty/dev/nginx/pythonfile/syscall table'

    sys_dict = {}

    with open(file_path) as f:
        for line in f:
            if '//' not in line:
                temp_str = line.replace(' ', '')
                temp_str = temp_str.replace('(syscall.ENOSYS)', '')
                temp_str = temp_str.replace(',', '')
                sys_list = temp_str.split(':')
                sysno = sys_list[0]
                sysname = sys_list[1]
                sys_dict[int(sysno)] = sysname
    return sys_dict