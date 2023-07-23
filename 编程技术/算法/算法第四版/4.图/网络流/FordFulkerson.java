public class FordFulkerson {
    // 最短增广路径的Ford-Fulkerson最大流量算法
    // 网络的初始流量为零,沿着任意从起点到重点(且不含有饱和的正向边或是空逆向边)的增广路径增大流量,
    // 直到网络中不存在这样的路径为止

    private static final double FLOATING_POINT_EPSILON = 1E-11; // 处理浮点舍入错误

    private final int V;    // 顶点数量
    private boolean[] marked;   // 在剩余网络中是否存在路径 s->v
    private FlowEdge[] edgeTo;  // s->v的最短路径上的最后一条边
    private double value;   // 当前最大流量

    // 计算从s到t的流量网络G的最大流量配置
    public FordFulkerson(FlowNetwork G, int s, int t) {
        V = G.V();
        if(!isFeasible(G, s, t)) throw new IllegalArgumentException("Initial flow is infeasible");

        // 当存在增广路径时
        value = excess(G, t);   // 终点t的净流入量
        while (hasAugmentingPath(G, s, t)) {    // 利用所有存在的增广路径
            // 计算当前的瓶颈容量
            double bottle = Double.POSITIVE_INFINITY;
            for(int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            // 增大流量
            for(int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }

        // 检查是否为最佳条件
        assert check(G, s, t);
    }

    // 返回最大流量的值
    public double value()  {
        return value;
    }

    // 返回指定顶点与起点在最小切分的起点侧
    public boolean inCut(int v)  {
        return marked[v];
    }

    // 返回是否存在增广路径,如果存在增广路径,则终止时edgeTo[]将包含增广路径的父链接
    // 此实现将找到最短增广路径
    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];   // 路径上的最后一条边
        marked = new boolean[G.V()];    // 标记路径已知的起点

        // 广度优先搜索
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);   // 将起点入队
        marked[s] = true;   // 标记起点

        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.dequeue();

            for(FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if(e.residualCapacityTo(w) > 0) {
                    if(!marked[w]) {    // 在剩余网络中对于任意一条连接到一个未被标记的顶点的边
                        edgeTo[w] = e;
                        marked[w] = true;
                        queue.enqueue(w);
                    }
                }
            }
        }
        // 返回是否有增广路径
        return marked[t];
    }

    // 返回顶点v的净流量(流入量-流出量)
    private double excess(FlowNetwork G, int v) {
        double excess = 0.0;
        for(FlowEdge e : G.adj(v)) {
            if(v == e.from())
                excess -= e.flow();
            else
                excess += e.flow();
        }
        return excess;
    }

    // 检查流量是否可行
    private boolean isFeasible(FlowNetwork G, int s, int t) {
        // 检查是否满足容量限制
        for(int v = 0; v < G.V(); v++) {
            for(FlowEdge e : G.adj(v)) {
                if(e.flow() < -FLOATING_POINT_EPSILON || e.flow() > e.capacity() + FLOATING_POINT_EPSILON)
                    return false;
            }
        }
        // 检查当前最大流量<起点流出量和终点流入量
        if(Math.abs(value + excess(G, s)) > FLOATING_POINT_EPSILON)    // 当前最大流量-起点流出量>0
            return false;
        if(Math.abs(value - excess(G, t)) > FLOATING_POINT_EPSILON)    // 当前最大流量-终点流入量>0
            return false;

        // 检查流入顶点的净流量为0(起点s和终点t除外)
        for(int v = 0; v < G.V(); v++) {
            if(v == s || v == t)
                continue;
            else if(Math.abs(excess(G, v)) > FLOATING_POINT_EPSILON)   // 顶点v净流入量>0
                return false;
        }
        return true;
    }

    // 检查是否为最佳条件
    private boolean check(FlowNetwork G, int s, int t) {
        // 检查流量是否可行
        if(!isFeasible(G, s, t))
            return false;

        // 检查s是最小切分的起点侧并且终点t不在起点侧
        if(!inCut(s))  // 起点不在最小切分的起点侧
            return false;
        if(inCut(t))   // 终点在最小切分的起点侧
            return false;

        // 检查最小切分的值等于最大流量的值
        double mincutValue = 0.0;
        for(int v = 0; v < G.V(); v++) {
            for(FlowEdge e : G.adj(v)) {
                if((v == e.from()) && inCut(e.from()) && !inCut(e.to()))
                    mincutValue += e.capacity();    // 最小切分的流量值
            }
        }
        if(Math.abs(mincutValue - value) > FLOATING_POINT_EPSILON)  // 最小切分的流量值-当前最大流量值>0
            return false;
        return true;
    }
}
