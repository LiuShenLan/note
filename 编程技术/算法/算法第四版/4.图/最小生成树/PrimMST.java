public class PrimMST {
    // 最小生成树的Prim算法的即时实现

    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private Edge[] edgeTo;  // 非树顶点距离树最近的边
    private double[] distTo;    // distTo[v] = edgeTo[v].weight()
    private boolean[] marked;   // 如果v在树中则为true
    private IndexMinPQ<Double> pq;  // 有效的横切边

    // 在加权无向图中计算最小生成树
    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for(int v = 0; v < G.V(); v++) // 在每个顶点运行
            if(!marked[v])
                prim(G, v); // minimum spanning forest

        assert check(G);
    }

    // 最小生成树的Prim算法的即时实现,起点为s
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    // 将顶点v添加到树中,更新数据
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(Edge e : G.adj(v)) {
            int w = e.other(v);
            if(marked[w])  // 顶点w已经在最小生成树之中
                continue;   // 边v-w失效
            if(e.weight() < distTo[w]) {   // 连接w和树的最佳边Edge变为e
                distTo[w] = e.weight(); // 树到顶点w的权重更新为e.weight()
                edgeTo[w] = e;

                if(pq.contains(w))
                    pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    // 返回最小生成树的边
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for(int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if(e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    // 返回最小生成树的权重和
    public double weight() {
        double weight = 0.0;
        for(Edge e : edges())
            weight += e.weight();
        return weight;
    }


    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // 检查权重
        double totalWeight = 0.0;
        for(Edge e : edges())
            totalWeight += e.weight();
        if(Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // 检查是否无环
        UF uf = new UF(G.V());
        for(Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if(uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for(Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if(uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // 检查是否是最小生成森林(最优切割条件)
        for(Edge e : edges()) {
            // mst中除了e的所有的边
            uf = new UF(G.V());
            for(Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if(f != e) uf.union(x, y);
            }
            // 检查边e是否切割中权重最小的边
            for(Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if(uf.find(x) != uf.find(y)) {
                    if(f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
