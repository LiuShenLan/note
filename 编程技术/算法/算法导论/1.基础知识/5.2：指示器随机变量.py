
import random

"""
将数组随机排列
"""
def random_in_place(a):
    for i in range(len(a)):
        a[i],a[random.randint(i,len(a)-1)] = a[random.randint(i,len(a)-1)],a[i]

"""
取range(n)中的长度为m的随机子集
"""
def random_sample(m,n):
    if m==0:
        return []
    else:
        S=random_sample(m-1,n-1)
        i=random.randint(0,n-1)
        if i in S:
            S.append(n)
        else:
            S.append(i)
    return S


if __name__ == '__main__':
    a=[13,3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7]
    print("指示器随机变量")

    print("将数组{}随机排列:".format(a))
    random_in_place(a)
    print("输出为{}".format(a))

    n=10
    m=5
    print("取range({})中的长度为{}的随机子集{}".format(n,m,random_sample(m,n)))