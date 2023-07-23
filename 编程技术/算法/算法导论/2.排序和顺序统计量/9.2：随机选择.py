import random

"""
随机选择    O(n)
"""
# partition_randomized 函数有bug
def randomized_select(a,low,high,select):
    # 返回数组a中第i小的数值
    if low==high-1:
        return a[low]
    mid=partition(a,low,high)    # 随机选择一个数值作为主元,将数组分为比主元小和比主元大两部分
    k=mid-low+1     # 小于等于主元的数值的数目
    if select==k:
        return a[mid]
    elif select<k:
        return randomized_select(a,low,mid,select)
    else:
        return randomized_select(a,mid,high,i-k)

def randomized_select_loop(a,low,high,select):  # 循环版
    while True:
        # 返回数组a中第i小的数值
        if low>=high-1:
            return a[low]
        mid=partition(a,low,high)    # 随机选择一个数值作为主元,将数组分为比主元小和比主元大两部分
        k=mid-low+1     # 小于等于主元的数值的数目
        if select==k:
            return a[mid]
        elif select<k:
            high=mid
        else:
            low=mid+1
            select=select-k
    
    
def partition(a,low,high):
    mid=a[high-1]       # 数组中值,移动数值使得<mid位于数组左侧,>mid位于数组右侧
    mid_index=low-1     # 数组中值下标
    count_same=0
    for i in range(low,high-1):
        if a[i]<=mid:   # 此时值<mid
            mid_index+=1    # 将中值下标右移(添加一个小于中值的数值)
            a[mid_index],a[i]=a[i],a[mid_index] # 将较小值移动到mid_index左侧
            if a[i]==mid:
                count_same+=1
    a[mid_index+1],a[high-1]=a[high-1],a[mid_index+1]
    return mid_index+1-int(count_same/2)

def partition_randomized(a,low,high):   # mid在low与high之间随机采样
    i=random.randint(low,high-1)
    a[i],a[high-1]=a[high-1],a[i]
    return partition(a,low,high)
    

if __name__ == '__main__':
    a=[6,4,8,2,3,9,1,7,5]
    i=6
    print("随机选择:输入{}".format(a))
    while True:
        b=randomized_select(a,0,len(a),i)
        print("第{}小的数值为:{}".format(i,b))
        a=[6,4,8,2,3,9,1,7,5]
        b=randomized_select_loop(a,0,len(a),i)
        print("循环版第{}小的数值为:{}".format(i,b))
        
