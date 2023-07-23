### 各种最大流量算法的性能特点

|算法|在含有V个顶点和E条边的流量网络中(各边容量最大为C),算法的运行时间在最坏情况下的增长数量级|
|:-:|:-:|
|最短增广路径的Ford-Fulkerson算法|V*E^2|
|最大容量的Ford-Fulkerson算法|E^2*logC|
|预流推进算法(preflow-push)|EV*log(E/V^2)|
