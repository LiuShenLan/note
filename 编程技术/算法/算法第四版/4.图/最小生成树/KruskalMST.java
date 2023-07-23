public class KruskalMST {
    // 最小生成树的Kruskal算法

    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;  // 最小生成树的权重
    private Queue<Edge> mst = new Queue<>();    // 最小生成树的边

    // 计算加权无向图的最小生成树
    public KruskalMST(EdgeWeightedGraph G) {
        // 传递边的数组更有效率
        MinPQ<Edge> pq = new MinPQ<>();
        for(Edge e : G.edges())
            pq.insert(e);

        // 贪心算法
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();   // 从pq得到权重最小的边和它的顶点
            int v = e.either();
            int w = e.other(v);
            if(uf.find(v) != uf.find(w)) { // 将边v-w加入最小生成树之后不会产生环
                uf.union(v, w);  // 合并分量
                mst.enqueue(e);  // 将边添加到最小生成树之中
                weight += e.weight();
            }
        }

        assert check(G);
    }

    // 返回最小生成树的边
    public Iterable<Edge> edges() {
        return mst;
    }

    // 返回最小生成树的总权重
    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // 检查权重
        double total = 0.0;
        for(Edge e : edges())
            total += e.weight();
        if(Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
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
