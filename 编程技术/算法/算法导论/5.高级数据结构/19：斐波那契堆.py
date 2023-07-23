"""
斐波那契堆
"""
# 最小堆:父节点<=子节点

import math

class NODE:
    def __init__(self, key=None):
        self.key = key
        self.degree = 0     # 子节点链表中的子节点的数目
        self.p = None
        self.child = None   # 指向任意一个子节点的指针
        self.left =None
        self.right = None
        self.mark = False

    def search_child(self,key):     # 在以该节点为根的堆中搜索相应关键字
        w=self
        v=self
        res=None
        while 1:
            if w.key==key:
                return w
            if w.child!=None:
                res=w.child.search_child(key)
            if res!=None:
                return res
            w=w.right
            if w==v:
                return None

class FIB_HEAP():
    def __init__(self,n=0,min_node=None):
        self.n=n            # 堆中节点数目
        self.min=min_node   # 堆中最小节点

    def insert(self,x):
        x.degree=0
        x.p=None
        x.child=None
        x.mark=False
        if self.min==None:  # 堆为空
            self.min=x
            self.min.left=self.min
            self.min.right=self.min
        else:   # 堆中有节点
            x.right=self.min
            x.left=self.min.left
            x.left.right=x
            x.right.left=x
            if x.key<self.min.key:
                self.min=x
        self.n+=1

    def extract_min(self):  # 删除并返回最小节点
        z=self.min
        if z!=None: # 堆非空
            # 若self.min有子节点,则将其子节点变为根节点
            if z.child!=None:
                c=z.child
                while c.p!=None:
                    c_l=c.left
                    c.p=None
                    c.right=self.min
                    c.left=self.min.left
                    c.left.right=c
                    c.right.left=c
                    c=c_l
            z.right.left=z.left
            z.left.right=z.right
            z.child=None
            if z==z.right:  # z为根链表中唯一节点
                self.min=None
            else:
                self.min=z.right
                self.consolidate()
            self.n=self.n-1
        return z

    def consolidate(self):        
        A=[]
        for i in range(int(math.log(self.n,1.61803)+1)):
            A.append(None)
        w=self.min
        t=w.left
        # 将根节点链表进行合并处理
        while 1:
            x=w
            d=x.degree
            temp=w.right
            while A[d]!=None:
                y=A[d]
                if x.key > y.key:
                    x,y=y,x     # 保证x.key<y.key
                self.link(y,x)  # 将y变为x的子节点
                A[d]=None
                d+=1
            A[d]=x
            if w==t:
                break
            w=temp
        self.min=None
        for i in range(int(math.log(self.n,1.61803)+1)):
            if A[i]!=None:
                if self.min==None:
                    self.min=A[i]
                    self.min.left=A[i]
                    self.min.right=A[i]
                else:
                    A[i].right=self.min
                    A[i].left=self.min.left
                    A[i].left.right=A[i]
                    A[i].right.left=A[i]
                    if A[i].key<self.min.key:
                        self.min=A[i]

    def link(self,y,x):     # 移动y为x的子节点
        y.right.left=y.left
        y.left.right=y.right
        y.p=x
        x.degree+=1
        y.mark=False
        if x.child==None:   # x无子节点,直接添加y为x的子节点
            x.child=y
            y.left=y
            y.right=y
        else:
            c=x.child
            y.right=c
            y.left=c.left
            y.right.left=y
            y.left.right=y

    def decrease_key(self,x,k): # 将x.key减小至k
        if not isinstance(x,NODE):
            x=self.find_node(x)
        if k>x.key:
            raise ValueError("error: new key is greater than current key!")
        x.key=k
        y=x.p
        if y!=None and x.key<y.key: # 若x不是子节点,且x.key>其父节点的key
            self.cut(x,y)   # 切断子节点x与其父节点y之间的联系,使x成为根节点
            self.cascading_cut(y)
        if x.key<self.min.key:
            self.min=x

    def cut(self,node_c,node_p):    # 切断子节点与父节点之间的联系,使子节点成为根节点
        if node_p.degree==1:
            node_p.child=None
            node_c.p=None
        else:
            w=node_c.right
            w.left=node_c.left
            w.left.right=w
            if node_c==node_p.child:
                node_p.child=w
        node_p.degree-=1
        self.insert(node_c)     # 使子节点成为根节点
        node_c.p=None
        node_c.mark=False
    
    def cascading_cut(self,y):  # 在cut(x,y)之后执行cascading_cut(y)以维护斐波那契堆性质
        z=y.p
        if z!=None:
            if y.mark==False:
                y.mark=True
                # 在某时刻,node曾经是根节点,然后node被连接到某个节点上成为了子节点
                # 若node的两个子节点被切断移除,则将node与其父节点切断,使node成为根节点
                # 因此开始node.mark=F,当一个子节点被切断之后node.mark=T
            else:
                # 此时y.mark=T,说明y的一个子节点已经被切断,并且又经历了子节点被切断的操作
                # 因此将y与其父节点切断,使y成为根节点,并对z进行递归检验
                self.cut(y,z)
                self.cascading_cut(z)

    def delete(self,x): # 删除节点x
        self.decrease_key(x,float("-inf"))
        self.extract_min()

    def find_node(self,key):    # 查找关键字为输入key的节点
        w=self.min
        res=None
        if w.key>key:
            return None
        else:
            cr=w
            while 1:
                if cr.key==key:
                    return cr
                else:
                    if cr.child!=None:
                        res=cr.child.search_child(key)
                    if res!=None:
                        return res
                    cr=cr.right
                    if cr==w:
                        return None


def fib_heap_union(h1,h2):  # 合并堆h1与h2,返回新堆
    h=FIB_HEAP()
    h.min=h1.min
    h2.min.left=h1.min
    h2.min.right=h1.min.right
    h2.min.left.right=h2.min
    h2.min.right.left=h2.min
    if h1.min==None or (h2.min!=None and h2.min.key<h1.min.key):
        h.min=h2.min
    h.n=h1.n+h2.n
    return h



        

if __name__ == '__main__':
    print("斐波那契堆")
    data_input=[1,9,10,5,23,17,3,21,28,30,19,25,15,14,20,27,2,24,11,29,31,6,12,18,16,26,4,7,22,13,8,80]
    fib=FIB_HEAP()
    for i in range(len(data_input)):
        fib.insert(NODE(key=data_input[i]))
    print(fib.extract_min().key)
    fib.delete(2)
