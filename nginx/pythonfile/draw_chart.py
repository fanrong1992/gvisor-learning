import matplotlib.pyplot as plt
import numpy as np
import collect_syscall as cs
import get_syscall as gs

syscall_dict = gs.get_syscall_dict()

# 创建一个点数为 8 x 6 的窗口, 并设置分辨率为 80像素/每英寸
plt.figure(figsize=(20, 15), dpi=80)

# 再创建一个规格为 1 x 1 的子图
plt.subplot(1, 1, 1)

syslist = cs.get_syscall_list()

# 柱子总数
N = len(syslist)
# 包含每个柱子对应值的序列
values = [item[1] for item in syslist]
# print(values)

# 包含每个柱子下标的序列
index = np.arange(N)

# 柱子的宽度
width = 0.8

# 绘制柱状图, 每根柱子的颜色为紫罗兰色
p2 = plt.barh(index, values, width, color=['tomato', 'darkorange', 'teal', 'darkmagenta', 'steelblue'], tick_label=[syscall_dict[item[0]] for item in syslist])

for a,b in zip(index, values):
     plt.text(b, a+0.4, syscall_dict[syslist[a][0]], ha='left', va= 'top', fontsize=15)
#     plt.text(a, b-10, '%.0f' % b, ha='center', va= 'bottom',fontsize=11)

# 设置横轴标签
plt.ylabel('syscall name')
# 设置纵轴标签
plt.xlabel('times')

# 添加标题
plt.title('syscall from APP to sentry')



# 添加纵横轴的刻度
plt.yticks(index, [item[0] for item in syslist], fontsize=15)
plt.xticks(np.arange(0, syslist[0][1], 10), fontsize=15)

# # 添加图例
# plt.legend(loc="upper right")

plt.show()