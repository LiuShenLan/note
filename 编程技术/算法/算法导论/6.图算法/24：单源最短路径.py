"""
单源最短路径
"""

class NODE():
    def __init__(self,value=None,p=None,c=[],weight=None,rank=0):
        self.value=value
        self.p=p
        self.c=c
        self.weight=weight
        self.rank=rank

        self.d=None # 从源节点到该节点的最短路径权重的上界,既从源节点到该节点的最短路径估计

class GRAPH():
    def __init__(self):
        self.adj=[] # 长度为结点数的序列,其中元素为链表,存储了与结点相连的结点
        self.v=[]   # 图结点集合,顺序与self.adi相同
        self.e=[]   # 边集合

        # 深度优先搜索参数
        self.color_d="white"
        self.p_d=None
        self.d_d=None   # 记录结点第一次被发现的时间
        self.f_d=None   # 记录对该节点的邻接链表完成扫描的时间
        self.cc_d=None  # 连通分量,若结点连通分量相同,则结点处于相同的连通分量之中

        # 拓扑排序参数
        self.topological_sort_list=[]

    def get_index_adj(self,node):   # 返回结点在self.adj[]中的下标
        for i,node_adj in enumerate(self.v):
            if node_adj==node:
                return i
        return None
    
    def initialize_single_source(self,node_source): # 对图中的节点以及源节点进行初始化
        for node in self.v:
            node.d=float("inf")
            node.p=None
        node_source.d=0
    def relax(self,u,v,weight): # 根据边(u,v)更新v的最短路径估计
        if v.d>u.d+weight[u][v]:
            v.d=u.d+weight[u][v]
            v.p=u

    def Bellman_Ford(self,node_source,weight):   # 允许边的权重为负,返回布尔值表示是否存在权重为负的环路    O(VE)
        self.initialize_single_source(node_source)
        for _ in range(len(self.v)):
            for edge in self.e:
                self.relax(edge[0],edge[1],weight)
        for edge in self.e:
            if edge[1].d>edge[0].d+weight[edge[0]][edge[1]]:
                return False    # 图包含可以从源节点到达的权重为负的环路
        return True # 图不包含负环路
    
    def topological_sort(self): # 拓扑排序(有向无环图)
        # 利用深度优先算法,将所有结点排为一行,使得所有边都是从左指向右
        self.topological_sort_list=[]
        self.dfs()
        # 既将结点按照完成时间从大到小排序
    def dfs(self):  # 深度优先搜索
        for node in self.v:
            node.color_d="white"
            node.p_d=None
        self.time=0
        self.cc=0
        for node in self.v:
            if node.color_d=="white":
                node.cc_d=self.cc
                self.dfs_visit(node)
    def dfs_visit(self,node):
        self.time+=1
        node.d_d=self.time      # 结点发现时间
        node.color_d="black"
        for node_c in self.adj[self.get_index_adj(node)]:
            if node_c.color_d=="white":
                node_c.cc_d=node.cc_d
                node_c.p_d=node
                self.dfs_visit(node_c)
        self.time+=1
        node.f_d=self.time
        self.topological_sort_list.append(node)

    def dag_shortest_paths(self,node_source,weight):    # O(V+E)
        self.topological_sort()
        self.initialize_single_source(node_source)
        for u in self.topological_sort_list: # 遍历拓扑排序中的节点
            for v in self.adj[self.get_index_adj(u)]:
                self.relax(u,v,weight)
    
    def dijkstra(self,node_source,weight):  # 要求所有边的权重非负
        self.initialize_single_source(node_source)
        s=[]    # 已处理完的节点的集合
        q=self.v.copy() # v-s,未处理的节点集合
        while q!=[]:
            u=extract_min(q)
            s.append(u)
            for v in self.adj[self.get_index_adj(u)]:
                self.relax(u,v,weight)

def extract_min(q): # 删除并返回d(最短路径估计)最小的节点值
    min_d=float("inf")
    min_index=0
    for index,node in enumerate(q):
        if node.d<min_d:
            min_index=index
    return q.pop(min_index)

if __name__ == '__main__':
    print("单源最短路径")