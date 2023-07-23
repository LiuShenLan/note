"""
数论算法
"""

import random

"""
最大公约数
任何数均整除0
d|a且d|b -> d|(ax+by) / d|gcd(a,b)    x,y为整数
a|b且b|a -> a=+-b
gcd(a,b)为不同时为0的整数a和b的最大公约数
gcd(0,0)=0
gcd(a,b)=gcd(b,a)
gcd(a,b)=gcd(-a,b)
gcd(a,b)=gcd(|a|,|b|)
gcd(a,0)=|a|
gcd(a,ka)=|a|     k为整数
gcd(a,gcd(b,c))=gvd(gcd(a,b),c)
gcd(a,b)是ax+by中最小的正元素     x,y为整数
gcd(an,bn)=n*gcd(a,b)
n|ab且gcd(a,n)=1 -> n|b
gcd(a,b)=1 -> a,b成为互质数
gcd(a,p)=1且gcd(b,p)=1 -> gcd(ab,p)=1
对整数n1,n2,...,nk，若对任何i!=j,有gcd(ni,nj)=1,则称n1,n2,...,nk两两互质
若p|ab -> p|a或p|b
合数能唯一的分解为素数的幂的乘积
gcd(a,b)=gcd(b,a mod b)
"""

def euclid(a,b):    # 欧几里得算法
    # 返回a,b的最大公约数d和满足d=ax+by的x,y
    if b==0:
        return a,1,0
    else:
        d,x_,y_=euclid(b,a%b)
        x=y_
        y=x_-int(a*1.0/b)*y_
        return d,x,y

def euclid_while(a,b):
    while b!=0:
        a,b=b,a%b
    return a

def modular_linear_equation_solver(a,b,n):      # 求解满足ax=b(mod n)的x
    d,x_,y_=euclid(a,n)
    if d%b==0:
        x0=(x_*b/d)%n
        for i in range(d):
            print((x0+i*n/d)%n)
    else:
        print("no solutions")

def modular_exponentiation(a,b,n):  # 用反复平方法快速计算a^b mod n
    c=0
    d=1
    b_=[b]  # b_为b的二进制表示,共有k+1位,bk,bk-1,...,b0
    for i in range(len(b_),-1,-1):
        c=2*c
        d=(d**2)%n
        if b_[i]==1:
            c+=1
            d=(d*a)%n
    return d

def witness(a,n):
    n_1=n-1
    if n_1%2==1:    # n-1=2**t * u
        t=0
        u=n_1
    else:
        t=1
        while True:
            if int(n_1/(2**t))==n_1/(2**t):
                t+=1
            else:
                u=int(n_1/(2**t))
                break
    x=[]
    x.append(modular_exponentiation(a,u,n))
    for i in range(t):
        x.append((x[-1]**2)%n)
        if x[-1]==1 and x[-2]!=1 and x[-2]!=n-1:
            return True
    if x[-1]!=1:
        return True
    return False

def miller_rabin(n,s):  # 随机测试的次数
    for j in range(s):
        a=random.randint(1,n-1)
        if witness(a,n):
            return "{} 是合数".format(n)
    return "{} 是素数".format(n)



if __name__ == '__main__':
    print("数论算法")