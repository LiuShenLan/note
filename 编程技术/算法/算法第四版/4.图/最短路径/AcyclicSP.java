public class AcyclicSP {
    // 无环加权有向图的最短路径算法

    /*
    若要计算无环加权无向图中的最长路径
    则只需将distTo[]的初始化值变为Double.NEGATIVE_INFINITY
    并改变relax()方法中的不等式的方向
    */

    private double[] distTo;    // 最短路径 s->v 的距离
    private DirectedEdge[] edgeTo;  // 最短路径 s->v 顶点v的上一条边


    // 在有向无环图中计算最短路径树
    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // 按照拓扑排序访问顶点
        Topological topological = new Topological(G);

        if(!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");

        for(int v : topological.order())
            for(DirectedEdge e : G.adj(v))
                relax(e);
    }

    // 松弛边e
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if(distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    // 返回从起点s到顶点v的最短路径距离
    public double distTo(int v) {
        return distTo[v];
    }

    // 是否存在从起点s到顶点v的路径
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    //返回从起点s到顶点v的最短路径
    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
}
