"""
字符串匹配
"""
# 文本t,模式(子字符串p)

# 朴素字符串匹配算法
def naive_string_matcher(t,p):
    n=len(t)
    m=len(p)
    for s in range(n-m+1):
        if p==t[s:s+m]:
            print("有效偏差s={}".format(s))

# rabin_karp算法
def rabin_karp_matcher(t,p,d,q):    # 基数d,素数q
    n=len(t)
    m=len(p)
    h=(d**(m-1))%q
    p=0
    t=0
    for i in range(m):
        p=(d*p+p[i])%q
        t=(d*t+t[i])%q
    for s in range(n-m+1):
        if p==t:
            if p==t[s:s+m]:
                print("有效偏差s={}".format(s))
        if s<n-m+1:
            t=(d*(t-t[s+1]*h)+t[s+m+1])%q

# 利用有限自动机
def finite_automaton_matcher(t,d,m):    # 转移函数d,最终状态m
    n=len(t)
    q=0
    for i in range(n):
        q=d[q][d[i]]
        if q==m:
            print("有效偏差s={}".format(i-m))

# KMP算法
def kmp_matcher(t,p):
    n=len(t)
    m=len(p)
    pi=compute_prefix_function(p)
    q=0
    for i in range(n):
        while q>0 and p[q]!=t[i]:
            q=pi[q-1]
        if p[q]==t[i]:
            q+=1
        if q==m:
            print("有效偏差s={}".format(i-m+1))
            q=pi[-1]

def compute_prefix_function(p):
    m=len(p)
    pi=[0]
    k=0
    for q in range(1,m):
        while k>0 and p[k]!=p[q]:
            k=pi[k-1]
        if p[k]==p[q]:
            k+=1
        pi.append(k)
    return pi

def kmp_algorithm(t,p): # KMP算法
    if not p:
        return 0
    if not t:
        return -1
    
    m=len(p)

    pi=[0]
    k=0
    for i in range(1,m):
        while k>0 and p[k]!=p[i]:
            k=pi[k-1]
        if p[k]==p[i]:
            k+=1
        pi.append(k)
    
    q=0
    for i in range(len(t)):
        while q>0 and p[q]!=t[i]:
            q=pi[q-1]
        if p[q]==t[i]:
            q+=1
        if q==m:
            return i-m+1
    return -1

if __name__ == '__main__':
    print("字符串匹配")
    kmp_matcher([1,2,1,2,1,3,1,11,11,1,2,1,2,1,3,1],[1,2])