"""
基本的图算法
"""
# 邻接矩阵,使用VxV的矩阵表示图
# 稠密图(E接近V^2)效果较好,适宜快速判断任意两节点之间是否有边连接

# 邻接链表
# 表示稀疏图(E<<V^2)时非常紧凑,效果较好
class NODE():
    def __init__(self,value=None,next_node=None,weight=None):
        self.value=value
        self.next=next_node
        self.weight=weight

        # 广度优先搜索参数
        self.color_h="white"
        self.p_h=None # 前驱结点(父节点)
        self.d_h=float("inf") # 源节点与该结点直接的距离

        # 深度优先搜索参数
        self.color_d="white"
        self.p_d=None
        self.d_d=None   # 记录结点第一次被发现的时间
        self.f_d=None   # 记录对该节点的邻接链表完成扫描的时间
        self.cc_d=None  # 连通分量,若结点连通分量相同,则结点处于相同的连通分量之中

        # 拓扑排序参数
        self.topological_sort_list=[]

class GRAPH():
    def __init__(self):
        self.adj=[] # 长度为结点数的序列,其中元素为链表,存储了与结点相连的结点
        self.v=[]   # 图结点集合,顺序与self.adi相同

        # 深度优先搜索参数
        self.time=0
        self.cc=0
        
        # 计算强连通分量参数
        self.adj_t=[]   # 计算图G的转置(所有的边反向)
        self.v_f_d_sort=[]    # 将结点按照深度优先搜索的结束时间降序排列

    def get_index_adj(self,node):   # 返回结点在self.adj[]中的下标
        for i,node_adj in enumerate(self.v):
            if node_adj==node:
                return i
        return None
    
    def transpose(self):    # 计算图G的转置,填充self.adj_t[]
        for i in range(len(self.adj)):
            for node in self.adj[i]:
                self.adj_t[self.get_index_adj(node)].append(self.v[i].copy())
        # 将结点按照深度优先搜索的结束时间降序排列
        self.v_f_d_sort=self.v.copy()
        for i in range(len(self.v_f_d_sort)):
            for j in range(len(self.v_f_d_sort)-1,i,-1):
                if self.v_f_d_sort[j-1].f_d<self.v_f_d_sort[j].f_d:
                    self.v_f_d_sort[j-1],self.v_f_d_sort[j]=self.v_f_d_sort[j],self.v_f_d_sort[j-1]
    
    def bfs(self,node_s):   # 广度优先搜索(breadth first search)
        for node in self.v:
            if node==node_s:
                continue
            else:
                node.color_h="white"
                node.d_h=float("inf")
                node.p_h=None
        node_s.color_h="black"
        node_s.d_h=0
        node_s.p_h=None
        q=[]    # 暂存所有新发现的结点的队列(先进先出)
        q.append(node_s)
        while q!=[]:
            node_p=q.pop(index=0)
            for node_c in self.adj[self.get_index_adj(node_p)]:
                if node_c.color_h=="white":
                    node_c.color_h="black"
                    node_c.d=node_p.d_h+1
                    node_c.p=node_p
                    q.append(node_c)
    
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
        # 若为拓扑排序,则加入下一行
        # self.topological_sort_list.append(node)

    def topological_sort(self): # 拓扑排序(有向无环图)
        # 利用深度优先算法,将所有结点排为一行,使得所有边都是从左指向右
        self.topological_sort_list=[]
        self.dfs()  # 注意self.dfs_visit()有修改
        # 既将结点按照完成时间从大到小排序
    
    def strongly_connected_components(self):    # 计算强连通分量(在同一个强连通分量中,所有的结点可以相互到达)
        self.dfs()
        self.transpose()
        self.dfs_t()

    def dfs_t(self):  # 深度优先搜索(针对图G的转置)
        for node in self.v:
            node.color_d="white"
            node.p_d=None
        self.time=0
        self.cc=0
        for node in self.v_f_d_sort:
            if node.color_d=="white":
                node.cc_d=self.cc
                self.dfs_visit(node)
    
    def dfs_visit_t(self,node):
        self.time+=1
        node.d_d=self.time      # 结点发现时间
        node.color_d="black"
        for node_c in self.adj_t[self.get_index_adj(node)]:
            if node_c.color_d=="white":
                node_c.cc_d=node.cc_d
                node_c.p_d=node
                self.dfs_visit_t(node_c)
        self.time+=1
        node.f_d=self.time
    
    def print_path(self,node_root,node_leaf):   # 打印两节点之间的路径
        if node_leaf==node_root:
            print(node_leaf.value)
        elif node_leaf.p_d==None:
            raise ValueError("路径不存在")
        else:
            self.print(node_root,node_leaf.p_d)
            print(node_leaf.value)

if __name__ == '__main__':
    print("基本的图算法")