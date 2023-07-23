"""
线性规划
"""

import numpy as np

# a[i][j]*x[j]<=b[i]    松弛变量:x[n+i]=b[i]-a[i][j]*x[j]   x[n+i]>=0 (等式左边为基本变量，等式右边为非基本变量)

def initialize_simplex(a,b,c):  # 计算标准型线性规划的初始基本解是可行的松弛型
    # a:系数矩阵
    # b:常数项向量
    # c:目标函数系数

    k=np.argmin(b)      # b中最小值的下标
    if b[k]>=0:
        m=a.shape[0]
        n=a.shape[1]
        return np.array(range(n)),np.array(range(n,n+m)),a,b,c,0
    # Laux为在输入线性规划的所有条件左侧添加-x0,目标函数设置为-x0
    # 令(N,B,a,b,c,v)为Laux的松弛结果
    pass
    l=n+k
    # Laux含有n+1个非基本变量和m个基本变量
    N,B,a,b,c,v=pivot(N,B,a,b,c,v,l,0)
    # Laux的基础解是可行的
    while True in c>0:
        e=np.where(c>0)[0][0]   # c中第一个>0的值的下标
        for i in B:
            if a[i][e]>0:
                d[i]=b[i]/a[i][e]
            else:
                d[i]=np.inf
        l=np.argmin(d)      # d中最小值的下标
        if d[l]==np.inf:
            return "unbounded(无界)"
        else:
            N,B,a,b,c,v=pivot(N,B,a,b,c,v,l,e)
    # 如果Laux目标函数-x0=0
    #   如果x0是基本变量
    #       通过pivot使x0为非基础变量
    #   从Laux的最终的松弛型中移除x0
    #   返回改变后的松弛型
    # 返回"infeasible,不可行"

def pivot(N,B,a,b,c,v,l,e):     # 转动，将等式左侧的基本变量xl替出，将等式右侧的非基本变量xe替入
    # N:非基本变量下标的集合，len(N)=n
    # B:基本变量下标的集合，len(b)=m
    # a:系数矩阵,m*n
    # b:常数项向量
    # c:目标函数系数
    # v:目标函数常数项
    # l:等式左侧的基本变量xl
    # e:等式右侧的非基本变量xe

    # 计算新基本变量xe的系数
    a_=np.zeros([len(b),len(N)])
    b_=b.copy()
    b_[e]=b[l]/a[l][e]
    for j in N:
        if j==e:
            continue
        else:
            a_[e][j]=a[l][j]/a[l][e]
    a_[e][l]=1/a[l][e]
    
    # 计算其余约束的系数
    for i in B:
        if i==l:
            continue
        else:
            b_[i]=b[i]-a[i][e]*a_[e][j]
            for j in N:
                if j==e:
                    continue
                else:
                    a_[i][j]=a[i][j]-a[i][e]*a_[e][j]
            a_[e][l]=-a[i][e]*a_[e][l]
    
    # 计算目标函数
    v_=v+c[e]*b_[e]
    c_=c.copy()
    for j in N:
        if j==e:
            continue
        else:
            c_[j]=c[j]-c[e]*a_[e][j]
    c_[l]=-c[e]*a_[e][l]

    # 计算新的(非)基本变量的集合
    N.pop(e)
    N.append(l)
    B.pop(l)
    B.append(e)

    return N,B,a_,b_,c_,v_

def simplex(a,b,c):     # 求解标准型线性规划
    N,B,a,b,c,v=initialize_simplex(a,b,c)
    # N:非基本变量下标的集合，len(N)=n
    # B:基本变量下标的集合，len(b)=m
    # a:系数矩阵
    # b:常数项向量
    # c:目标函数系数
    d=np.zeros(len(B))      # xe增加的值得幅度限制向量
    x_=np.zeros(len(N))

    while True in c>0:
        e=np.where(c>0)[0][0]   # c中第一个>0的值的下标
        for i in B:
            if a[i][e]>0:
                d[i]=b[i]/a[i][e]
            else:
                d[i]=np.inf
        l=np.argmin(d)      # d中最小值的下标
        if d[l]==np.inf:
            return "unbounded(无界)"
        else:
            N,B,a,b,c,v=pivot(N,B,a,b,c,v,l,e)
    for i in range(len(N)):
        if i in B:
            x_[i]=b[i]
        else:
            x_[i]=0
    return x_






if __name__ == '__main__':
    print("线性规划")
    