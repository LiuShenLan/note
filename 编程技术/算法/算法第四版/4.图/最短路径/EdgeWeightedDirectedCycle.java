public class EdgeWeightedDirectedCycle {
    // 处理负权重环扩展最短路径
    private boolean[] marked;   // marked[v] = 顶点v是否被标记
    private DirectedEdge[] edgeTo;  // 最短路径 s->v 的最后一条边
    private boolean[] onStack;  // 顶点v是否在栈中
    private Stack<DirectedEdge> cycle;  // 有向环,如果无环则为null

    // 确定加权有向图是否含有有向环
    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!marked[v])
                dfs(G, v);

        assert check();
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();

            if(cycle != null)  // 发现了环
                return ;
            else if(!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if(onStack[w]) {
                cycle = new Stack<>();
                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return ;
            }
        }
        onStack[v] = false;
    }

    // 加权有向图是否含有有向环
    public boolean hasCycle() {
        return cycle != null;
    }

    // 如果加权有向图含有有向环则返回有向环,否则返回null
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    // 检查有向图是否有环
    private boolean check() {
        if(hasCycle()) {
            DirectedEdge first = null, last = null;
            for(DirectedEdge e : cycle()) {
                if(first == null)
                    first = e;
                if(last != null)
                    if(last.to() != e.from())
                        return false;

                last = e;
            }
            if(last.to() != first.from())
                return false;
        }
        return true;
    }
}
