public class KosarajuSharirSCC {
    // 计算有向图强连通分量的Kosaraju算法
    // 在图G上 按照图G的反向图的 逆后序排列 进行深度优先搜索

    private boolean[] marked;   // 已访问过的顶点
    private int[] id;   // 包含顶点v的强连通分量标志符
    private int count;  // 强连通分量数量

    // 计算强连通分量
    public KosarajuSharirSCC(Digraph G) {
        // 计算反向图
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        // 在图G上按照反向图的逆后序排列 进行深度优先搜索
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v : order.reversePost()) {
            if(!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

        assert check(G);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)) {
            if(!marked[w])
                dfs(G, w);
        }
    }

    // 返回强连通分量数量
    public int count() {
        return count;
    }

    // 返回顶点v和顶点w是否在同一个强连通分量之中
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    // 返回顶点v所在的强连通分量id
    public int id(int v) {
        return id[v];
    }

    private boolean check(Digraph G) {
        TransitiveClosure tc = new TransitiveClosure(G);
        for(int v = 0; v < G.V(); v++) {
            for(int w = 0; w < G.V(); w++) {
                if(stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                    return false;
            }
        }
        return true;
    }
}
