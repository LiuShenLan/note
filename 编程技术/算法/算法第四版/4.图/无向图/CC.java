public class CC {
    // 使用深度优先搜索找出无向图中的所有连通分量

    private boolean[] marked;   // 顶点v是否被标记
    private int[] id;   // 顶点v所属的连通分量
    private int[] size; // 连通分量id所包含的顶点数
    private int count;  // 连通分量数目

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // 计算加权图的连通分量
    public CC(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // 无向图的深度优先搜索
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // 加权图的深度优先搜索
    private void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for(Edge e : G.adj(v)) {
            int w = e.other(v);
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }


    // 返回顶点v所属的连通分量id
    public int id(int v) {
        return id[v];
    }

    // 返回顶点v所属的连通分量id所包含的顶点数
    public int size(int v) {
        return size[id[v]];
    }

    // 返回连通分量的数目
    public int count() {
        return count;
    }

    // 顶点v和w是否在同一个连通分量之中
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }
}
