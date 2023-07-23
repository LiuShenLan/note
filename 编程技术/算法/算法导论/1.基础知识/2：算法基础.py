"""
插入排序
"""
def ex2_1_1():
    a=[31,41,59,26,41,58]
    print("2.1-1: a =",a)

    for j in range(1,len(a)):
        key=a[j]
        i=j-1
        while i>=0 and a[i]>key:
            a[i+1]=a[i]
            i=i-1
        a[i+1]=key
    print(a)

# 2.1-2
def ex2_1_2():
    a=[31,41,59,26,41,58]
    print("2.1-2: a =",a)
    for j in range(1,len(a)):
        key=a[j]
        i=j-1
        while i>=0 and a[i]<key:
            a[i+1]=a[i]
            i=i-1
        a[i+1]=key
    print(a)

"""
线性查找
"""
def ex2_1_3():
    a=[31,41,59,26,41,58]
    v=41
    print("2.1-3: a =",a,"v =",v)
    output = []
    for i in range(len(a)):
        if a[i]==v:
            output.append(i)
    if len(output)==0:
        output=None
    print("output=",output)

#2.1-4
def ex2_1_4():
    a=[0,1,1,0,1,0,1,1,1,1,1,0,1]
    b=[0,1,0,0,0,1,1,0,0,0,1,1,1]
    c=[0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    d=0
    for i in range(len(a)):
        c[i]=(a[i]+b[i]+d)%2
        d=int((a[i]+b[i]+d)/2)
    c[-1]=d
    print("2.1-4: a =",a)
    print("       b =",b)
    print("       c =",c)

"""
选择算法
"""
def ex2_2_2():
    a=[31,41,59,26,41,58]
    print("2.2-2: a =",a)
    for i in range(len(a)-1):
        index_min=i
        for j in range(i+1,len(a)):
            if a[index_min]>a[j]:
                index_min=j
        a[i],a[index_min]=a[index_min],a[i]
    print(a)

"""
归并排序
"""
def ex2_3_1():
    def merge(a,p,q,r):
        """
        a:输入数组,该数组根据p<=q<=r截取出两部分,这两部分分别为已经排列好的子数组
        返回数组a,其中p<=r部分为将两个子数组合并后的排列好的数组
        """
        L=[]
        R=[]
        for i in range(p,q):
            L.append(a[i])
        for j in range(q,r):
            R.append(a[j])
        L.append(float("inf"))
        R.append(float("inf"))
        i=0
        j=0
        for k in range(p,r):
            if L[i]<R[j]:
                a[k]=L[i]
                i+=1
            else:
                a[k]=R[j]
                j+=1
    def merge_sort(a,p,r):
        if p<r-1:
            q=int((p+r)/2)
            merge_sort(a,p,q)
            merge_sort(a,q,r)
            merge(a,p,q,r)
    a=[3,41,59,26,38,57,9,49]
    print("2.3-1: a =",a)
    merge_sort(a,0,len(a))
    print(a)

"""
归并排序
"""
def ex2_3_2():
    def merge_no_inf(a,p,q,r):
        L=[]
        R=[]
        for i in range(p,q):
            L.append(a[i])
        for j in range(q,r):
            R.append(a[j])
        i=0
        j=0
        for k in range(p,r):
            if i>q-p-1:
                a[k]=R[j]
                j+=1
            elif j>r-q-1:
                a[k]=L[i]
                i+=1
            elif L[i]<R[j]:
                a[k]=L[i]
                i+=1
            else:
                a[k]=R[j]
                j+=1
    def merge_sort_no_inf(a,p,r):
        if p<r-1:
            q=int((p+r)/2)
            merge_sort_no_inf(a,p,q)
            merge_sort_no_inf(a,q,r)
            merge_no_inf(a,p,q,r)
    a=[3,41,59,26,38,57,9,49,100]
    print("2.3-2: a =",a)
    merge_sort_no_inf(a,0,len(a))
    print(a)

"""
二分查找
"""
def ex2_3_5():
    def binary_search_1(a,low,high,v):
        while low<=high:
            mid=int((low+high)/2)
            if v==a[mid]:
                return mid
            elif v>a[mid]:
                low=mid+1
            else:
                high=mid-1
        return -1
    def binary_search_2(a,low,high,v):
        if low>high:
            return -1
        mid=int((low+high)/2)
        if v==a[mid]:
            return mid
        elif v>a[mid]:
            return binary_search_2(a,mid+1,high,v)
        else:
            return binary_search_2(a,low,mid-1,v)
    a=[2,4,6,8,10,12,14,16,18,20]
    v=2
    print("2.3-5: a =",a,"v =",v)
    print(binary_search_1(a,0,int(len(a)),v))
    print(binary_search_2(a,0,int(len(a)),v))

"""
冒泡排序
"""
def tk2_2():
    def bubble_sort(a):
        for i in range(len(a)):
            for j in range(len(a)-1,i,-1):
                if a[j-1]>a[j]:
                    a[j-1],a[j]=a[j],a[j-1]
    a=[3,41,59,26,38,57,9,49,100]
    print("2-2: a =",a)
    bubble_sort(a)
    print(a)

"""
计算逆序对
"""
def tk2_4():
    def merge(a,p,q,r):
        L=[]
        R=[]
        for i in range(p,q):
            L.append(a[i])
        for j in range(q,r):
            R.append(a[j])
        L.append(float("inf"))
        R.append(float("inf"))
        i=0
        j=0
        num=0
        for k in range(p,r):
            if L[i]<R[j]:
                a[k]=L[i]
                i+=1
            else:
                a[k]=R[j]
                j+=1
                num+=q-p-i
        return num
    def merge_count_inversions(a,p,r):
        if p<r-1:
            q=int((p+r)/2)
            left=merge_count_inversions(a,p,q)
            right=merge_count_inversions(a,q,r)
            num=+merge(a,p,q,r)
            return left+right+num
        return 0
    a=[1,2,4,3]
    print("2-2: a =",a)
    num=merge_count_inversions(a,0,len(a))
    print(num)




if __name__ == '__main__':
    # ex2_1_1()
    # ex2_1_2()
    # ex2_1_3()
    # ex2_1_4()
    # ex2_2_2()
    # ex2_3_1()
    # ex2_3_2()
    # ex2_3_5()
    # tk2_2()
    tk2_4()