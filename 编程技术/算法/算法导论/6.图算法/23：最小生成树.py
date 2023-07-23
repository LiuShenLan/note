"""
最小生成树
"""
# 生成树:连接一个图中所有的节点,并且无环的树

class NODE():
    def __init__(self,value=None,p=None,c=[],weight=None,rank=0):
        self.value=value
        self.p=p
        self.c=c
        self.weight=weight
        self.rank=rank

        # Prim参数
        self.key=None   # 连接节点与最小生成树中节点的所有边的最小边的权重
        self.p_Prim=None    # 节点在最小生成树中的父节点

class GRAPH():
    def __init__(self):
        self.adj=[] # 长度为结点数的序列,其中元素为链表,存储了与结点相连的结点
        self.v=[]   # 图结点集合,顺序与self.adi相同
        self.e=[]   # 边集合

        self.set=[] # 不相交集合序列

        # Prim参数
        self.root=None  # 最小生成树根节点
    
    def get_index_adj(self,node):   # 返回结点在self.adj[]中的下标
        for i,node_adj in enumerate(self.v):
            if node_adj==node:
                return i
        return None

    def bubble_sort(self,a):
        for i in range(len(a)):
            for j in range(len(a)-1,i,-1):
                if a[j-1]>a[j]:
                    a[j-1],a[j]=a[j],a[j-1]

    def make_set(self,node):
        node.rank=0
        node.p=node
        self.set.append(node)

    def find_set(self,node):
        if node.p!=node:
            node.p=self.find_set(node.p)    # 使x.p直接指向根节点
        return node.p   # 路径压缩

    def link(self,x,y):
        if x.rank>y.rank:   # 按秩合并
            y.p=x
            x.c.append(y)
        else:
            x.p=y
            y.c.append(x)
            if x.rank==y.rank:
                y.rank+=1
    def union(self,x,y):
        self.link(self.find_set(x),self.find_set(y))

def Kruskal(g):   # 属于贪心算法
    a=[]
    for node in g.v:
        g.make_set(node)
    g.bubble_sort(g.e)  # 将图中边按权重排序为递增(非减)序列
    for edge in g.e:
        if g.find_set(edge[0])!=g.find_set(edge[1]):
            a.append(edge)
            g.union(edge[0],edge[1])
            # 按照边的权重从小到大进行检查,若边不属于同一棵树,则将其加入最小生成树
    return a

def Prim(g,edge):
    g=GRAPH()
    node=NODE()
    for node in g.v:
        node.key=float("inf")
        node.p_Prim=None
    g.root.key=0
    q=g.v.copy()
    while q!=[]:
        u=extract_min(q)
        for node in g.adj[g.get_index_adj(u)]:
            if (node in q) and (edge[u,node]<node.key):
                node.p_Prim=u
                node.key=edge[u,node]

# 邻接矩阵表示版
"""
PRIM-ADJ(G, w, r):
    # w为权重
    initialize A with every entry = (NIL, ∞)
    T = {r}
    for i = 1 to V
        if Adj[r, i] != 0
            A[i] = (r, w(r, i))
    for each u in V - T
        k = min(A[i].2)
        T = T ∪ {k}
        k.π = A[k].1
        for i = 1 to V
            if Adf[k, i] != 0 and Adj[k, i] < A[i].2
                A[i] = (k, Adj[k, i])
"""

def extract_min(q): # 删除并返回key最小的节点值
    min_key=float("inf")
    min_index=0
    for index,node in enumerate(q):
        if node.key<min_key:
            min_index=index
    return q.pop(min_index)
            


if __name__ == '__main__':
    print("最小生成树")