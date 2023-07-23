public class TransitiveClosure {
    // 顶点对可达性的API
    // 使用有向图的可达性API DirectedDFS

    private DirectedDFS[] tc;  // tc[v] = reachable from v

    // 构建传递闭包
    public TransitiveClosure(Digraph G) {
        tc = new DirectedDFS[G.V()];
        for(int v = 0; v < G.V(); v++)
            tc[v] = new DirectedDFS(G, v);  // 有向图的可达性API(深度优先搜索)
    }

    // 图中是否存在顶点v到顶点w的路径
    public boolean reachable(int v, int w) {
        return tc[v].marked(w);
    }
}
