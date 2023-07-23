"""
动态规划
"""
# 步骤:1.刻画一个最优解的结构特征
# 2.递归的定义最优解的值
# 3.计算最优解的值,通常采用自底向上的方法
# 4.利用计算出的信息构建一个最优解

class CUT_ROD():
    # 左子节点>=父节点>=右子节点
    def __init__(self,price):
        self.p=price

    def top_down_memoized(self,n):  # 自顶向下
        r=[float('-inf') for i in range(n+1)]   # 各长度最优收益
        return self.top_down_memoized_aux(n,r)
    def top_down_memoized_aux(self,n,r):
        if  r[n]>=0:
            return r[n]
        if n==0:
            q=0
        else:
            q=float('-inf')
            for i in range(1,n+1):
                q=max(q,self.p[i]+self.top_down_memoized_aux(n-i,r))
        r[n]=q
        return q

    def top_down_memoized_extended(self,n):  # 自顶向下,返回切割方案
        r=[float('-inf') for i in range(n+1)]   # 各长度最优收益
        s=[float('-inf') for i in range(n+1)]   # 各长度第一段切割方案
        val,s=self.top_down_memoized_aux_extended(n,r,s)
        print("自顶向下,最优值为:{}\t切割方案为:".format(val))
        while n>0:
            print(s[n],end=' ')
            n=n-s[n]
    def top_down_memoized_aux_extended(self,n,r,s):
        if  r[n]>=0:
            return r[n]
        if n==0:
            q=0
        else:
            q=float('-inf')
            for i in range(1,n+1):
                val,s=self.top_down_memoized_aux_extended(n-i,r,s)
                if q<self.p[i]+val:
                    q=self.p[i]+val
                    s[n]=i
        r[n]=q
        return q,s
    
    def bottom_up(self,n):      # 自底向上版本
        r=[]        # 各长度最优收益
        r.append(0)
        for j in range(1,n+1):
            q=float('-inf')
            for i in range(1,j+1):
                q=max(q,self.p[i]+r[j-i])
            r.append(q)
        return r[n]

    def bottom_up_extended(self,n): # 返回切割方案
        r=[]        # 各长度最优收益
        r.append(0)
        s=[float('-inf') for i in range(n+1)]   # s[n]为长度为n时切割的第一节的长度
        for j in range(1,n+1):
            q=float('-inf')
            for i in range(1,j+1):
                if q<self.p[i]+r[j-i]:
                    q=self.p[i]+r[j-i]
                    s[j]=i
            r.append(q)
        return r,s
    
    def print_cut_solution(self,n): # 输出切割方案
        r,s=self.bottom_up_extended(n)
        print("最优值为:{}\t切割方案为:".format(r[n]),end='')
        while n>0:
            print(s[n],end=' ')
            n=n-s[n]
        print("")

if __name__ == '__main__':
    price=[0,1,5,8,9,10,17,17,20,24,30]
    cut=CUT_ROD(price)
    print("自顶向下,最优值为:{}".format(cut.top_down_memoized(10)))
    print("自底向上,最优值为:{}".format(cut.bottom_up(10)))
    cut.print_cut_solution(10)
    # cut.top_down_memoized_extended(10)  # 有bug
    print("各子问题解:",cut.bottom_up_extended(10))