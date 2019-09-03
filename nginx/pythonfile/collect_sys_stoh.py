import re
import matplotlib.pyplot as plt
import numpy as np

file_path = '/home/zty/dev/nginx/syslog/list'
file_tail = []
with open(file_path) as f:
    for line in f:
        temp_str = line[line.find(".")+1:-1]
        file_tail.append(temp_str)
# print(file_tail)

sys_dict = {}

for tail in file_tail:
    file_path = '/home/zty/dev/nginx/syslog/log.' + tail

    with open(file_path) as f:
        for line in f:
            if "exited with" not in line and "---" not in line:
                temp_str = line[:line.find("(")]
                # print(temp_str)
                if temp_str in sys_dict:
                    sys_dict[temp_str] += 1
                else:
                    sys_dict[temp_str] = 1
                #print(re.findall(r"\d+", temp_str))
                # sys_num = re.findall(r"\d+", temp_str)[0]
                # syscall_num_list[int(sys_num)][1] += 1

# 获取列表的第二个元素
def takeSecond(elem):
    return elem[1]

def get_syscall_dict():
    file_path = '/home/zty/dev/nginx/pythonfile/syscall table'

    sys_dict = {}

    with open(file_path) as f:
        for line in f:
            if '//' not in line and '??' not in line:
                temp_str = line.replace(' ', '')
                temp_str = temp_str.replace('(syscall.ENOSYS)', '')
                temp_str = temp_str.replace(',', '')
                sys_list = temp_str.split(':')
                sysno = sys_list[0]
                sysname = sys_list[1]
                sys_dict[sysname.lower()[:-1]] = int(sysno)
    return sys_dict

sys_list = [[item, sys_dict[item]] for item in sys_dict]
sys_list = sorted(sys_list, key=takeSecond, reverse=True)

sys_table= get_syscall_dict()
# print(sys_table)

# print(sys_list)

# 创建一个点数为 8 x 6 的窗口, 并设置分辨率为 80像素/每英寸
plt.figure(figsize=(20, 15), dpi=80)

# 再创建一个规格为 1 x 1 的子图
plt.subplot(1, 1, 1)

# 柱子总数
N = len(sys_list)
print("num of syscalls: ", N)

# 包含每个柱子对应值的序列
values = [i[1] for i in sys_list]
# print(values)

# 包含每个柱子下标的序列
index = np.arange(N)

# 柱子的宽度
width = 0.8

# 绘制柱状图, 每根柱子的颜色为紫罗兰色
p2 = plt.bar(index, values, width, color=['tomato', 'darkorange', 'teal', 'darkmagenta', 'steelblue'], tick_label=[i[0] for i in sys_list])

for a,b in zip(index, values):
#      plt.text(b, a+0.4, syscall_dict[syslist[a][0]], ha='left', va= 'top', fontsize=15)
    plt.text(a, b-10, '%.0f' % b, ha='center', va= 'bottom',fontsize=15)

# 设置横轴标签
plt.xlabel('syscall number', fontsize=15)
# 设置纵轴标签
plt.ylabel('times', fontsize=15)

# 添加标题
plt.title('syscall from sentry to Host', fontsize=15)


x = [i[0] for i in sys_list]
# print(x)
print([sys_table[i] for i in x])

# 添加纵横轴的刻度
plt.xticks(index, [sys_table[i[0]] for i in sys_list], fontsize=15)
plt.yticks(np.arange(0, sys_list[0][1], 5000), fontsize=15)

# # 添加图例
# plt.legend(loc="upper right")

# plt.show()