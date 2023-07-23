"""
归并排序    O(nlgn)
"""
def merge_sort_1(a):    # 使用无穷大
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
    merge_sort(a,0,len(a))

def merge_sort_2(a):    # 不使用无穷大
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
    merge_sort_no_inf(a,0,len(a))

if __name__ == '__main__':
    a=[31,41,59,26,41,58]
    print("归并排序:输入a={}".format(a))
    merge_sort_1(a)
    print("使用无穷排序后:a = {}".format(a))
    merge_sort_2(a)
    print("不使用无穷排序后:a={}".format(a))    