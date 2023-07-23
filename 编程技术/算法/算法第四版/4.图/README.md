### 典型无向图实现的性能复杂度
|数据结构|所需空间|添加一条边v-w|检查w和v是否相邻|便利v的所有相邻顶点|
|:-:|:-:|:-:|:-:|:-:|
|边的列表|E|1|E|E|
|邻接矩阵|V^2|1|1|V|
|邻接表|E+V|1|degree(V)|degree(V)|
|邻接集|E+V|logV|logV|logV+degree(V)|

### 有向图处理问题
|问题|解决方法|
|:-:|:-:|
|单点和多点的可达性|DirectedDFS|
|单点有向路径|DepthFirstDirectedPaths|
|单点最短有向路径|BreadthFirstDirectedPaths|
|有向环检测|DirectedCycle|
|深度优先的顶点排序|DepthFirstOrder|
|优先级限制下的调度问题|Topological|
|拓扑排序|Topological|
|强连通性|KosarajuSCC|
|顶点对的可达性|TransitiveClosure|

### 各种最小生成树算法的性能特点
(V个顶点E条边,最坏情况下的增长数量级)
|算法|空间|时间|
|:-:|:-:|:-:|
|延时的Prim算法|E|ElogE|
|即时的Prim算法|V|ElogV|
|Kruskal|E|ElogE|
|Fredman-Tarjan|V|E+VlogV|
|Chazelle|V|非常接近但还没有达到E|
|理想情况|V|E(?)|

### 最短路径算法的性能特点
|算法|局限|路径长度比较次数(一般)|路径长度比较次数(最坏)|所需空间|优势|
|:-:|:-:|:-:|:-:|:-:|:-:|
|Dijkstra算法(即时版本)|边的权重必须为正|ElogV|ElogV|V|最坏情况下仍有较好的性能|
|拓扑排序|只适用于无环加权有向图|E+V|E+V|V|是无环图中的最优算法|
|Bellman-Ford算法(基于队列)|不能存在负权重环|E+V|VE|V|适用领域广泛|
