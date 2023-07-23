"""
插入排序    O(n^2)
"""
def insertion_sort(a):
    for j in range(1,len(a)):
        # j左侧为已经排序好的数组
        key=a[j]    # 待插入数值
        i=j-1
        while i>=0 and a[i]>key:
            # 若带插入数值小于a[i],则将a[i]右移一位
            a[i+1]=a[i]
            i=i-1
        a[i+1]=key


if __name__ == '__main__':
    a=[31,41,59,26,41,58]
    print("插入排序:输入a={}".format(a))
    insertion_sort(a)
    print("排序后:a={}".format(a))