"""
桶排序    O(n)
"""
# 输入需要满足在某一区间的均匀分布
def bucket_sort(a):
    n=len(a)
    b=[[] for i in range(n)]
    for i in range(n):
        b[int(n*a[i])].append(a[i])
    for i in range(n):
        insertion_sort(b[i])
    return b


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
    a=[0.79,0.13,0.16,0.64,0.39,0.20,0.89,0.53,0.71,0.42]
    print("桶排序:输入a={}".format(a))
    b=bucket_sort(a)
    print("排序后:a={}".format(b))