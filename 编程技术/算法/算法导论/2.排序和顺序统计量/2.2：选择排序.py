"""
选择排序    O(n^2)
"""
def selection_sort(a):
    for i in range(len(a)-1):
        index_min=i     # 最小值下标
        for j in range(i+1,len(a)):
            # 遍历查找最小值
            if a[index_min]>a[j]:
                index_min=j
        a[i],a[index_min]=a[index_min],a[i]

if __name__ == '__main__':
    a=[31,41,59,26,41,58]
    print("选择排序:输入a={}".format(a))
    pass
    print("排序后:a={}".format(a))