public class LazyPrimMST {
    // 最小生成树(minimum spanning tree)的Prim算法的延时实现

    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;  // 最小生成树的总权重
    private Queue<Edge> mst;    // 最小生成树的边
    private boolean[] marked;   // 最小生成树的顶点
    private MinPQ<Edge> pq; // 横切边(包括失效的边)

    // 计算最小生成树
    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<>();    // 最小生成树的边
        pq = new MinPQ<>(); // 横切边
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) // 在所有顶点上运行Prim算法
            if(!marked[v])
                prim(G, v);     // get a minimum spanning forest

        assert check(G);
    }

    // Prim算法的延时实现
    private void prim(EdgeWeightedGraph G, int s) {
        scan(G, s);
        while (!pq.isEmpty()) { // 当最小生成树含有V-1条边时停止更好
            Edge e = pq.delMin();   // 从pq中得到权重最小的边
            int v = e.either(), w = e.other(v); // 边的两个顶点
            assert marked[v] || marked[w];  // 若两个顶点均不在mst中则报错

            if(marked[v] && marked[w])
                continue;   // 延时实现,两个顶点均在mst中时则跳过
            mst.enqueue(e); //将边e加入mst
            weight += e.weight();   // 计算mst的总权重

            // scan边e的另一顶点
            if(!marked[v])
                scan(G, v);
            if(!marked[w])
                scan(G, w);
        }
    }

    // 如果与顶点v连接的边e的另一端点没有添加到最小生成树中，则将边e添加到优先队列中
    private void scan(EdgeWeightedGraph G, int v) {
        assert !marked[v];  // 如果顶点v已经被标记则报错
        marked[v] = true;
        for(Edge e : G.adj(v))
            if(!marked[e.other(v)])    // 边e的另一端点未被标记(不在最小生成树之中)
                pq.insert(e);
    }

    // 返回最小生成树的边
    public Iterable<Edge> edges() {
        return mst;
    }

    // 返回最小生成树的总权重
    public double weight() {
        return weight;
    }

    // 检查最佳条件
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
            for(Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if(f != e)
                    uf.union(x, y);
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
