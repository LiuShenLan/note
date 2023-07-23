public class DijkstraAllPairsSP {
    // 任意顶点对之间的最短路径

    private DijkstraSP[] all;

    // 在加权有向图中计算从每个顶点到其它所有顶点的最短路径
    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all  = new DijkstraSP[G.V()];
        for(int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }

    // 返回顶点s到顶点t的最短路径
    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    // 是否可以从顶点s到达顶点t
    public boolean hasPath(int s, int t) {
        return dist(s, t) < Double.POSITIVE_INFINITY;
    }

    // 返回从顶点s到顶点t的最短路径
    public double dist(int s, int t) {
        return all[s].distTo(t);
    }
}
