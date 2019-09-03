import re

def get_syscall_list():

    path = '/tmp/runsc/runsc.log.20190903-092244.614982.boot'

    syscall_num_list = [[i, 0] for i in range(402)]

    # 获取列表的第二个元素
    def takeSecond(elem):
        return elem[1]

    with open(path) as f:
        for line in f:
            if 'catch 1' in line:
                #print(re.findall(r"", line))
                temp_str = line[line.find("Syscall"):]
                #print(re.findall(r"\d+", temp_str))
                sys_num = re.findall(r"\d+", temp_str)[0]
                syscall_num_list[int(sys_num)][1] += 1
                
    syscall_num_list.sort(key=takeSecond, reverse=True)
    # print(syscall_num_list)
    index = 0
    while syscall_num_list[index][1] > 0:
        index += 1
    print("number of syscalls: ", index)
    print(syscall_num_list[:index])
    return syscall_num_list[:index]

syscall_list = get_syscall_list()