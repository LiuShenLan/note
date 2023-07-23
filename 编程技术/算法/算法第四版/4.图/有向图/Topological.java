public class Topological {
    // 拓扑排列
    // 一幅有向无环图的拓扑排序即为所有顶点的逆后序排列

    private Iterable<Integer> order;    // 顶点的拓扑排序
    private int[] rank; // 顶点v的拓扑顺序

    /**
     * Determines whether the digraph {@code G} has a topological order and, ifso,
     * finds such a topological order.
     * @param G the digraph
     */
    public Topological(Digraph G) {
        DirectedCycle finder = new DirectedCycle(G);    // 寻找有向环
        if(!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);   // 有向图中基于深度优先搜索的顶点排序
            order = dfs.reversePost();

            rank = new int[G.V()];
            int i = 0;
            for(int v : order)
                rank[v] = i++;
        }
    }

    // 返回顶点的拓扑排序
    public Iterable<Integer> order() {
        return order;
    }

    // 返回图是否可以拓扑排序
    public boolean hasOrder() {
        return order != null;
    }

    // 返回顶点v在拓扑排序中的顺序
    public int rank(int v) {
        if(hasOrder())
            return rank[v];
        else
            return -1;
    }
}
