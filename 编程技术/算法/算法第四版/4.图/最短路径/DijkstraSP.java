public class DijkstraSP {
    // 解决权重非负的加权有向图的单起点最短路径问题

    private double[] distTo;    // 最短路径s->v的权重
    private DirectedEdge[] edgeTo;  // 最短路径s->v的顶点v的最后一条边
    private IndexMinPQ<Double> pq;  // 顶点的优先队列

    // 计算加权有向图从起点s到其他所有顶点的最短路径
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        for(DirectedEdge e : G.edges())
            if(e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // 按照到起点s距离的远近松弛顶点
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for(DirectedEdge e : G.adj(v))
                relax(e);
        }

        // check optimality conditions
        assert check(G, s);
    }

    // 松弛边e,如果有更改则更新pq
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if(distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if(pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }

    // 返回从起点s到顶点v的最短路径长度
    public double distTo(int v) {
        return distTo[v];
    }

    // 从起点s是否可以到达顶点v
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // 返回从起点s到顶点v的最短路径
    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }


    // check optimality conditions:
    // 对于所有的边: distTo[e.to()] <= distTo[e.from()] + e.weight()
    // 对于最短路径中所有的边: distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDigraph G, int s) {

        // 检查图中边的权重是非负的
        for(DirectedEdge e : G.edges())
            if(e.weight() < 0)
                return false;

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

        return true;
    }
}
