import random

"""
快速排序    最坏O(n^2),期望O(nlgn)
"""
def quick_sort(a,low,high):
    if low<high-1:
        mid=partition(a,low,high)
        quick_sort(a,low,mid)
        quick_sort(a,mid+1,high)

def quick_sort_tail(a,low,high):    # 尾递归版本
    while low<high-1:
        mid=partition(a,low,high)
        if mid<(low+high)/2:
            quick_sort_tail(a,low,mid)
            low=mid+1
        else:
            quick_sort_tail(a,mid+1,high)
            high=mid

def quick_sort_randomized(a,low,high):  # 随机化版本
    if low<high-1:
        mid=partition_randomized(a,low,high)
        quick_sort(a,low,mid)
        quick_sort(a,mid+1,high)

def quick_sort_same(a,low,high):  # 处理相同元素的情况
    if low<high-1:
        same_low,same_high=partition_same(a,low,high)
        quick_sort_same(a,low,same_low)
        quick_sort_same(a,same_high+1,high)

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

def partition_same(a,low,high):     # 将若中间值相同,则返回中间值的左右下标
    mid=a[low]       # 数组中值,移动数值使得<mid位于数组左侧,>mid位于数组右侧
    same_low=low
    same_high=low
    for i in range(low+1,high):
        if a[i]<mid:   # 此时值<mid
            a[i],a[same_high+1],a[same_low]=a[same_high+1],a[same_low],a[i]
            same_high+=1
            same_low+=1
        elif a[i]==mid:
            a[i],a[same_high+1]=a[same_high+1],a[i]
            same_high+=1
    return same_low,same_high

if __name__ == '__main__':

    a=[13,19,9,5,128,7,4,21,2,6,11]
    print("快速排序:输入a={}".format(a))
    quick_sort(a,0,len(a))
    print("排序后:a = {}".format(a))

    a=[13,19,9,5,128,7,4,21,2,6,11]
    quick_sort_tail(a,0,len(a))
    print("尾递归版:a={}".format(a))

    a=[13,19,9,5,128,7,4,21,2,6,11]
    quick_sort_randomized(a,0,len(a))
    print("随机版:a = {}".format(a))

    a=[9,13,19,9,5,128,9,9,9,9,9,9,7,4,21,2,6,11,9]
    print("快速排序(重复版):输入a={}".format(a))
    quick_sort_same(a,0,len(a))
    print("排序后:a={}".format(a))
