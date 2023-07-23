"""
van Emde Boas树
"""
# 关键字限制为0~n-1的整数且不能重复
# 用n表示树中当前元素的个数,用u表示可能的取值范围(要求为2的幂)
# 因此关键字取值的全域为{0,1,...,u-1}

# 这个代码不大行,憋看了

import math

class NODE():
    def __init__(self,u):
        self.u=u
        self.u_up=2**(int(math.log(self.u,2)/2+1))
        self.u_down=2**(int(math.log(self.u,2)/2))
        self.min=None
        self.max=None
        if self.u!=2:
            self.summary=None
            self.cluster=[]
    
    def high(self,x):
        return int(x/self.u_down)

    def low(self,x):
        return x%self.u_down

    def index(self,x,y):
        return x*self.u_down+y
    
    def minimum(self):
        return self.min
    
    def maximun(self):
        return self.max

class FIB_HEAP():
    def __init__(self):
        pass

    def insert(self,node,x):
        if node.min==None:
            self.empty_insert(node,x)
        elif x<node.min:
            x,node.min=node.min,x
            if node.u>2:
                if node.cluster[node.high(x)]==None:
                    self.insert(node.summary,node.high(x))
                    self.empty_insert(node.cluster[node.high(x)],node.low(x))
                else:
                    self.insert(node.cluster[node.high(x)],node.low(x))
            if x>node.max:
                node.max=x
    
    def delete(self,node,x):
        if node.min==node.max:
            node.min=None
            node.max=None
        elif node.u==2:
            if x==0:
                node.min=1
            else:
                node.min=0
            node.max=node.min
        elif x==node.min:
            first_cluster=node.summary.min
            x=node.index(first_cluster,node.cluster[first_cluster])
            node.min=x
            self.delete(node.cluster[node.high(x)],node.low(x))
            if node.summary[node.high(x)]==None:
                self.delete(node.summary,node.high(x))
                if x==node.max:
                    summary_max=node.summary.max
                    if summary_max==None:
                        node.max=node.min
                    else:
                        node.max=node.index(summary_max,node.cluster[summary_max].max)
            elif x==node.max:
                node.max=node.index(node.high(x),node.cluster[node.high(x)])
    
    def empty_insert(self,node,x):  # 向空节点中插入
        node.min=x
        node.max=x
    
    def member(self,node,x): # 返回x是存在
        if x==node.min or x==node.max:
            return True
        elif node.u==2:
            return False
        else:
            return self.member(node.cluster[node.high(x)],node.low(x))
    
    def successor(self,node,x): # 后继
        if node.u==2:
            if x==0 and node.max==1:
                return 1
            else:
                return None
        elif node.min!=None and x<node.min:
            return node.min
        else:
            max_low=node.cluster[node.high(x)].max
            if max_low!=None and node.low(x)<max_low:
                offset=self.successor(node.cluster[node.high(x)],node.low(x))
                return node.index(node.high(x),offset)
            else:
                succ_cluster=self.successor(node.summary,node.high(x))
                if succ_cluster==None:
                    return None
                else:
                    offset=node.cluster[succ_cluster].min
                    return node.index(succ_cluster,offset)

    def predecessor(self,node,x): # 后继
        if node.u==2:
            if x==1 and node.max==0:
                return 0
            else:
                return None
        elif node.max!=None and x>node.max:
            return node.max
        else:
            min_low=node.cluster[node.high(x)].min
            if min_low!=None and node.low(x)>min_low:
                offset=self.predecessor(node.cluster[node.high(x)],node.low(x))
                return node.index(node.high(x),offset)
            else:
                pred_cluster=self.predecessor(node.summary,node.high(x))
                if pred_cluster==None:
                    if node.min!=None and x>node.min:
                        return node.min
                    else:
                        return None
                else:
                    offset=node.cluster[pred_cluster].max
                    return node.index(pred_cluster,offset)

if __name__ == '__main__':
    print("van Emde Boas树")