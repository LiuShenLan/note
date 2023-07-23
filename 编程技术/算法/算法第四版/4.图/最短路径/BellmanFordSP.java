public class BellmanFordSP {
    // 基于队列的Bellman-Ford算法

    private static final double EPSILON = 1E-14;

    private double[] distTo;    // 最短路径 s->v 的长度
    private DirectedEdge[] edgeTo;  // 最短路径 s->v 的最后一条边
    private boolean[] onQueue;  // 顶点v是否位于队列中
    private Queue<Integer> queue;   // 正在被放松的顶点
    private int cost;   // relax()的调用次数
    private Iterable<DirectedEdge> cycle;   // edgeTo[]中是否有负权重环

    // 计算加权有向图中从起点s到其它所有顶点的最短路径树
    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Bellman-Ford algorithm
        queue = new Queue<>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }

        assert check(G, s);
    }

    // 松弛顶点v,如果有修改则将另一顶点加入队列
    private void relax(EdgeWeightedDigraph G, int v) {
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight() + EPSILON) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if(++cost % G.V() == 0) {
                findNegativeCycle();
                if(hasNegativeCycle()) // 发现负权重环
                    return ;
            }
        }
    }

    // 起点s是否可以到达负权重环
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    // 返回从起点s可以到达的负权重环,如果没有负权重环则返回null
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    // 在前一图中找到环
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for(int v = 0; v < V; v++)
            if(edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    // 返回从起点s到顶点v的最短路径长度
    public double distTo(int v) {
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

    // 从起点s是否可以到达顶点v
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // 返回从起点s到顶点v的最短路径
    public Iterable<DirectedEdge> pathTo(int v) {
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    // check optimality conditions: either
    // (i) 是否存在从起点s可以到达的负权重环
    // (ii)  对于所有的边 e = v->w: distTo[w] <= distTo[v] + e.weight()
    // (ii') 对于最短路径中的所有的边 e = v->w: distTo[w] == distTo[v] + e.weight()
    private boolean check(EdgeWeightedDigraph G, int s) {
        // 检查是否含有负权重环
        if(hasNegativeCycle()) {
            double weight = 0.0;
            for(DirectedEdge e : negativeCycle())
                weight += e.weight();

            if(weight >= 0.0)
                return false;

        }  else {
            // 检查distTo[v]和edgeTo[v]是一致的
            if(distTo[s] != 0.0 || edgeTo[s] != null)
                return false;

            for(int v = 0; v < G.V(); v++) {
                if(v == s)
                    continue;
                if(edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY)
                    return false;
            }

            // 检查所有的边 e = v->w 满足 distTo[w] <= distTo[v] + e.weight()
            for(int v = 0; v < G.V(); v++) {
                for(DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if(distTo[v] + e.weight() < distTo[w])
                        return false;
                }
            }

            // 检查最短路径中的所有的边 e = v->w on SPT 满足 distTo[w] == distTo[v] + e.weight()
            for(int w = 0; w < G.V(); w++) {
                if(edgeTo[w] == null)
                    continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if(w != e.to())
                    return false;
                if(distTo[v] + e.weight() != distTo[w])
                    return false;
            }
        }
        return true;
    }
}
