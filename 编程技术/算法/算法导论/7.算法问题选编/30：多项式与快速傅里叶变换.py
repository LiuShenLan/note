"""
多项式与快速傅里叶变换
"""

# w的n次单位复数根满足:w^n=1，恰好有n个，为e^(2πi/n)，其中e^iθ=cosθ+isinθ

# 离散傅里叶变换DFT:多项式系数向量a的DFT为多项式在n个n次单位复数根处的多项式函数值
# 快速傅里叶变换FFT：将A(x)按照系数下标的奇偶分开

import math

def fft_recursive(a):
    # a为长度为n的系数序列，要求n为2的幂
    n=len(a)
    if n==1:
        return a
    wn=math.exp(complex(0,2*math.pi/n))
    w=1
    a_0=a[::2]
    a_1=a[1::2]
    y_0=fft_recursive(a_0)
    y_1=fft_recursive(a_1)
    y=[0 for i in range(n)]
    for k in range(0,n/2):
        y[k]=y_0[k]+w*y_1[k]
        y[k+n/2]=y_0[k]-w*y_1[k]
        w=w*wn
    return y

def fft_iterative(a):
    # 要求a的长度为2的幂
    A=a.copy()
    bit_reverse_copy(a,A)
    n=len(a)
    for s in range(1,math.log(n)+1):
        m=2**s
        wm=math.exp(complex(0,2*math.pi/m))
        for k in range():
            w=1
            for j in range(m/2):
                t=w*A[k+j+m/2]
                u=A[k+j]
                A[k+j]=u+t
                A[k+j+m/2]=u-t
                w=w*wm
    return A

def bit_reverse_copy(a,A):
    n=len(a)
    for k in range(n):
        A[rev(k)]=a[k]
def rev(k):
    return k    # 将k转换为二进制之后倒序，在转换成十进制返回

def dft_1_recursive(y):     # 求DFT的逆
    # a为长度为n的系数序列，要求n为2的幂
    n=len(y)
    if n==1:
        return y
    wn=1/math.exp(complex(0,2*math.pi/n))
    w=1
    y_0=y[::2]
    y_1=y[1::2]
    a_0=fft_recursive(y_0)
    a_1=fft_recursive(y_1)
    a=[0 for i in range(n)]
    for k in range(0,n/2):
        a[k]=(a_0[k]+w*a_1[k])/n
        a[k+n/2]=(a_0[k]-w*a_1[k])/n
        w=w*wn
    return a


if __name__ == '__main__':
    print("多项式与快速傅里叶变换")
    