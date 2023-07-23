"""
冒泡排序    O(n^2)
"""
def bubble_sort(a):
    def bubble_sort(a):
        for i in range(len(a)):
            for j in range(len(a)-1,i,-1):
                if a[j-1]>a[j]:
                    a[j-1],a[j]=a[j],a[j-1]
    bubble_sort(a)


if __name__ == '__main__':
    a=[31,41,59,26,41,58]
    print("冒泡排序:输入a={}".format(a))
    bubble_sort(a)
    print("排序后:a={}".format(a))