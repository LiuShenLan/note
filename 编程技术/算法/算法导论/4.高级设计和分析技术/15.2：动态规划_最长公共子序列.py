"""
动态规划
"""
# 步骤:1.刻画一个最优解的结构特征
# 2.递归的定义最优解的值
# 3.计算最优解的值,通常采用自底向上的方法
# 4.利用计算出的信息构建一个最优解

# 最长公共子序列:在给定的多个序列中,出现的顺序相同,但在给定序列中位置不一定相同,也不要求连续出现
# 若要求一个序列的最大单调递增子序列,则只需先将给定序列复制并排序,然后求最大公共子序列
import numpy as np

class LCS():
    def __init__(self,x,y):
        self.x=x
        self.y=y
    
    def length(self):
        m=self.x.shape[0]
        n=self.y.shape[0]
        b=np.zeros([m,n])
        c=np.zeros([m+1,n+1])
        for i in range(m):
            for j in range(n):
                if self.x[i]==self.y[j]:
                    c[i+1,j+1]=c[i,j]+1
                    b[i,j]=7    # 左上
                elif c[i,j+1]>=c[i+1,j]:
                    c[i+1,j+1]=c[i,j+1]
                    b[i,j]=8    # 上
                else:
                    c[i+1,j+1]=c[i+1,j]
                    b[i,j]=4    # 左
        return c,b
    
    def print_lcs_b(self,b,i,j):
        if i<=-1 or j<=-1:
            return
        if b[i,j]==7:
            self.print_lcs_b(b,i-1,j-1)
            print(self.x[i],end=' ')
        elif b[i,j]==8:
            self.print_lcs_b(b,i-1,j)
        else:
            self.print_lcs_b(b,i,j-1)
    
    def print_lcs_c(self,c,i,j):
        if c[i+1,j+1]==0:
            return
        if self.x[i]==self.y[j]:
            self.print_lcs_c(c,i-1,j-1)
            print(self.x[i],end=' ')
        elif c[i,j+1]>c[i+1,j]:
            self.print_lcs_c(c,i-1,j)
        else:
            self.print_lcs_c(c,i,j-1)

def long_monotonic(a):
    n=a.shape[0]
    b=np.empty([n])
    for i in range(n):
        b[i]=np.inf
    c=[[0]*n]
    l=1
    for i in range(n):
        if a[i]<b[0]:
            b[0]=a[i]
            c[0][0]=a[i]
        else:
            j=0
            while b[j]<=a[i]:
                j+=1
            b[j]=a[i]
            c[j]=c[j-1].copy()
            c[j].append(a[i])
            if j>l:
                l+=1
    print(c[l])

if __name__ == '__main__':
    x=np.array([1,0,0,1,0,1,0,1])
    y=np.array([0,1,0,1,1,0,1,1,0])
    lcs=LCS(x,y)
    c,b=lcs.length()
    print("最长公共子序列长度:",c[-1][-1])
    print("最长公共子序列:",end='')
    lcs.print_lcs_b(b,x.shape[0]-1,y.shape[0]-1)
    print("")
    print("最长公共子序列:",end='')
    lcs.print_lcs_c(c,x.shape[0]-1,y.shape[0]-1)
    print("")
    z=np.array([5,3,6,1,8,8,2,6,7,1,3,9,5])
    print("{}的最长递增子序列:".format(z),end='')
    long_monotonic(z)
