"""
计数排序    O(n)
"""
# 要求输入为0~k的自然数
def counting_sort(a):
    # k为输入a中的最大值
    b=a.copy()  # 设置b为与a长度相同的数组
    c=[0 for i in range(max(a)+1)]  # c的长度为0~a中的最大值
    for i in range(len(a)):
        c[a[i]]=c[a[i]]+1
        # 遍历数组a,数组a中的每一个值x=a[i]都使c[x]++
        # 此时数组c中的值c[i]为数组a中值为i的总数
    for i in range(1,len(c)):
        c[i]=c[i]+c[i-1]
        # 此时数组c中的值c[i]为数组a中值<=i的值总数
    for i in range(len(a)-1,-1,-1):
        b[c[a[i]]-1]=a[i]     # x=a[i],c[x]为数组a中<=x的值的总数,所以将x复制给b的第c[x]个
        c[a[i]]-=1
    return b

    

if __name__ == '__main__':
    a=[6,0,2,0,1,3,4,6,1,3,2]
    print("计数排序:输入{}".format(a))
    b=counting_sort(a)
    print("排序后:{}".format(b))
