"""
暴力算法
"""
def brute_force(a):
    sum_max=-float("inf")
    for i in range(len(a)):
        sum_temp=a[i]
        for j in range(i+1,len(a)):
            sum_temp+=a[j]
            if sum_temp>sum_max:
                sum_max=sum_temp
                low=i
                high=j
    return low,high,sum_max

"""
递归算法
"""
# 寻找横跨中点的最大子数组
def find_max_crossing_subarray(a,low,mid,high):
    sum_left=-float("inf")
    sum_temp=0
    for i in range(mid-1,low-1,-1):     # 从中点向左累加寻找最大子数组
        sum_temp+=a[i]
        if sum_temp>sum_left:
            sum_left=sum_temp
            index_left=i
    sum_right=-float("inf")
    sum_temp=0
    for j in range(mid,high):           # 从中点向右累加寻找最大子数组
        sum_temp+=a[j]
        if sum_temp>sum_right:
            sum_right=sum_temp
            index_right=j
    return index_left,index_right,sum_left+sum_right

# 递归步骤
def find_maximum_subarray(a,low,high):
    if low==high-1:   # 只剩一个元素
        return low,high,a[low]
    else:
        mid=int((low+high)/2)
        left_low,left_high,left_sum=find_maximum_subarray(a,low,mid)
        right_low,right_high,right_sum=find_maximum_subarray(a,mid,high)
        mid_low,mid_high,mid_sum=find_max_crossing_subarray(a,low,mid,high)
        if left_sum>right_sum and left_sum>mid_sum:
            return left_low,left_high,left_sum
        elif right_sum>left_sum and right_sum>mid_sum:
            return right_low,right_high,right_sum
        else:
            return mid_low,mid_high,mid_sum
    
"""
线性算法
"""
# 从数组的第一个元素开始,计算连续子数组和,当和为负数时舍弃,重新开始计算,记录其中最大的子数组
def iterative_find_maximum_subarray(a):
    sum_max=-float("inf")
    sum_temp=-float("inf")
    for i in range(len(a)):
        high_temp=i
        if sum_temp>0:
            sum_temp+=a[i]
        else:
            sum_temp=a[i]
            low_temp=i
        
        if sum_temp>sum_max:
            sum_max=sum_temp
            low=low_temp
            high=high_temp
    return low,high,sum_max

if __name__ == '__main__':
    a=[13,3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7]
    print("最大子数组问题,输入 =",a)

    # 暴力算法
    low,high,sum_max=brute_force(a)
    print("暴力算法:最大子数组范围:[{},{}],最大值:{}".format(low,high,sum_max))

    # 递归算法
    low,high,sum_max=find_maximum_subarray(a,0,len(a))
    print("递归算法:最大子数组范围:[{},{}],最大值:{}".format(low,high,sum_max))

    # 线性算法
    low,high,sum_max=iterative_find_maximum_subarray(a)
    print("线性算法:最大子数组范围:[{},{}],最大值:{}".format(low,high,sum_max))