"""
堆
"""
# 二叉树,最大堆为父节点>=子节点

import math

class HEAP():
    def __init__(self,a):
        self.data=a
        self.size=len(a)
    
    def left(self,i):   # 返回左子节点下标
        return 2*i+1
    def right(self,i):  # 返回右子节点下标
        return 2*i+2
    def parent(self,i): # 返回父节点下标
        return int((i-1)/2)

    def build_max_heap(self):   # 将堆转化为最大堆
        for i in range(int(self.size/2),-1,-1):
            self.max_heapify(i)

    def max_heapify(self,i):    # 维护最大堆的性质(递归)
        # 处理父节点>子节点的情况,前提是i的两个子二叉树均为最大堆
        l=self.left(i)
        r=self.right(i)
        largest=i
        if l<self.size and self.data[l]>self.data[largest]:
            largest=l
        if r<self.size and self.data[r]>self.data[largest]:
            largest=r
        if largest!=i:
            self.data[i],self.data[largest]=self.data[largest],self.data[i]
            self.max_heapify(largest)

    def build_min_heap(self):   # 将堆转化为最小堆
        for i in range(int(self.size/2),-1,-1):
            self.min_heapify(i)

    def min_heapify(self,i):    # 维护最小堆的性质(循环)
        # 处理父节点<子节点的情况,前提是i的两个子二叉树均为最小堆
        while True:
            l=self.left(i)
            r=self.right(i)
            smallest=i
            if l<self.size and self.data[l]<self.data[smallest]:
                smallest=l
            if r<self.size and self.data[r]<self.data[smallest]:
                smallest=r
            if smallest==i:
                break
            else:
                self.data[i],self.data[smallest]=self.data[smallest],self.data[i]
                i=smallest

    def sort(self,max_heap=True):    # 堆排序    O(nlgn)
        if max_heap==True:
            self.build_max_heap()
        else:
            self.build_min_heap()
        size=self.size
        for i in range(self.size-1,0,-1):
            self.data[0],self.data[i]=self.data[i],self.data[0]
            self.size-=1
            if max_heap==True:
                self.max_heapify(0)
            else:
                self.min_heapify(0)
        self.size=size
    
    def maximum(self):         # 返回最大值
        self.build_max_heap()
        return self.data[0]
    
    def extract_max(self):     # 删除并返回最大值  O(lgn)
        if self.size<0:
            raise IndexError("堆元素下标<0")
        temp_max=self.data[0]
        self.data[0]=self.data[self.size]
        self.size-=1
        self.max_heapify(0)
        return temp_max

    def insert_max(self,key):  # 添加一个新的值 O(lgn)
        self.size+=1
        if self.size<len(self.data):
            self.data[self.size]=-float("inf")
        else:
            self.data.append(-float("inf"))
        self.increase_key_max(self.size-1,key)
    
    def increase_key_max(self,i,key):  # 将原值更新    O(lgn)
        if key<self.data[i]:
            raise ArithmeticError("修改的值小于原值")
        self.data[i]=key
        while i>0 and self.data[i]>self.data[self.parent(i)]:
            self.data[i],self.data[self.parent(i)]=self.data[self.parent(i)],self.data[i]
            i=self.parent(i)
    
    def minimum(self):         # 返回最小值
        self.build_min_heap()
        return self.data[0]
    
    def extract_min(self):     # 删除并返回最小值  O(lgn)
        if self.size<0:
            raise IndexError("堆元素下标<0")
        temp_min=self.data[0]
        self.data[0]=self.data[self.size]
        self.size-=1
        self.min_heapify(0)
        return temp_min

    def insert_min(self,key):  # 添加一个新的值 O(lgn)
        self.size+=1
        if self.size<len(self.data):
            self.data[self.size]=float("inf")
        else:
            self.data.append(float("inf"))
        self.increase_key_min(self.size-1,key)
    
    def increase_key_min(self,i,key):  # 将原值更新    O(lgn)
        if key>self.data[i]:
            raise ArithmeticError("修改的值大于原值")
        while i>0 and self.data[i]>key:
            self.data[i]=self.data[self.parent(i)]
            i=self.parent(i)
        self.data[i]=key
    
    def delete_max(self,i):     # 删除一个值
        if self.data[i]>self.data[self.size-1]:
            self.data[i]=self.data[self.size-1]
            self.max_heapify(i)
        else:
            self.increase_key_max(i,self.data[self.size-1])
        self.size-=1
    
    def delete_min(self,i):
        if self.data[i]<self.data[self.size-1]:
            self.data[i]=self.data[self.size-1]
            self.min_heapify(i)
        else:
            self.increase_key_min(i,self.data[self.size-1])
        self.size-=1

    def print_shape(self):
        print("       {}       ".format(self.data[0]))
        print("   /       \\   ")
        print("   {}       {}   ".format(self.data[1],self.data[2]))
        print(" /   \\   /   \\ ")
        print(" {}   {}   {}   {} ".format(self.data[3],self.data[4],self.data[5],self.data[6]))
        num=self.size-7
        flag=1
        for i in range(num):
            if flag==1:
                print("/ ",end="")
                flag=1-flag
            else:
                print("\\ ",end="")
                flag=1-flag
        print("")
        for i in range(num):
            print("{} ".format(self.data[i+7]),end="")
        print("")


if __name__ == '__main__':
    print("堆排序")
    a=[3,5,0,2,7,8,6,9,1,4]
    print("原数列:{}".format(a))
    heap=HEAP(a)
    heap.build_max_heap()
    print("建立最大堆:")
    heap.print_shape()
    insert=10
    heap.insert_max(insert)
    print("最大堆中插入值{}:".format(insert))
    heap.print_shape()
    heap.sort(max_heap=True)
    print("堆排序:{}".format(heap.data))
    heap.build_min_heap()
    print("建立最小堆:")
    heap.print_shape()
    insert=-1
    heap.insert_min(insert)
    print("最小堆中插入值{}:".format(insert))
    heap.print_shape()
    index_delete=0
    print("删除第{}个元素".format(index_delete))
    heap.delete_min(index_delete)
    heap.print_shape()
    