public class DepthFirstSearch {
    // 无向图深度优先搜索

    private boolean[] marked;    // marked[v] = 是否存在s-v路径?
    private int count;           // 与起点s联通的顶点

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // 起点s与顶点v是否联通
    public boolean marked(int v) {
        return marked[v];
    }

    // 返回与起点联通的顶点的数目
    public int count() {
        return count;
    }
}
