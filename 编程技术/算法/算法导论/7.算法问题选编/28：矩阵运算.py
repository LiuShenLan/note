"""
矩阵运算
"""

import numpy as np

# LUP分解：非奇异矩阵A具有LUP分解使得PA=LU，其中L为单位下三角矩阵，U为上三角矩阵，P为置换矩阵(表示矩阵行列变换的单位矩阵)
#       Ax=b -> PAx=Pb -> LUx=Pb


def lup_solve(l,u,pi,b):    # 根据LUP求解x
    n=L.shape[0]
    x=np.zeros(n)
    y=np.zeros(n)
    for i in range(n):
        for j in range(i):
            y[i]=b[pi[i]]-l[i][j]*y[j]
    for i in range(n-1,-1,-1):
        for j in range(i+1,n):
            x[i]=y[i]-u[i][j]*x[j]
        x[i]=x[i]/u[i][i]

    return x

def lu_decomposition(a):    # LU分解
    n=a.shape[0]
    u=np.zeros([n,n])   # 初始化U使其对角线一下元素为0
    l=np.eye(n)         # 初始化L使其对角线为1,对角线上方元素为0    np.eye()为创建对角矩阵
    for k in range(n):
        u[k][k]=a[k][k]
        for i in range(k+1,n):
            l[i][k]=a[i][k]/u[k][k]
            u[k][i]=a[k][i]
        for i in range(k+1,n):
            for j in range(k+1,n):
                a[i][j]=a[i][j]-l[i][k]*u[k][j]
    return l,u

def lup_decomposition(a):   # LUP分解
    n=a.shape[0]
    pi=np.zeros(n)
    for i in range(n):  # 初始化pi表示恒等变换
        pi[i]=i
    for k in range(n):
        p=0
        for i in range(k,n):    # 遍历第k列寻找最大值
            if abs(a[i][k])>p:
                p=abs(a[i][k])
                k_=i
        if p==0:
            raise ValueError("输入为奇异矩阵")
        pi[k],pi[k_]=pi[k_],pi[k]   # 将最大值移到第一行
        for i in range(n):          # 将a的k和k_行进行交换以确定主元akk
            a[k][i],a[k_][i]=a[k_][i],a[k][i]
        for i in range(k+1,n):
            a[i][k]=a[i][k]/a[k][k]
            for j in range(k+1,n):
                a[i][j]=a[i][j]-a[i][k]*a[k][j]


if __name__ == '__main__':
    print("矩阵运算")
    