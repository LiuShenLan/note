public class DirectedCycle {
    // 寻找有向环

    private boolean[] marked;   // 深度优先搜索中顶点v是否被标记
    private int[] edgeTo;   // 到达顶点v的路径上的前一个顶点
    private boolean[] onStack;  // 递归调用期间栈上的所有顶点
    private Stack<Integer> cycle;   // 有向环中的所有顶点(如果存在)

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        marked  = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!marked[v] && cycle == null)
                dfs(G, v);
    }

    // 运行深度优先搜索以查找有向环
    private void dfs(Digraph G, int v) {
        onStack[v] = true;

        marked[v] = true;
        for(int w : G.adj(v)) {
            if(cycle != null)
                return ;
            else if(!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }else if(onStack[w]) {  // 反向跟踪有向环
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    // 返回有向图是否含有有向环
    public boolean hasCycle() {
        return cycle != null;
    }

    // 返回有向环
    public Iterable<Integer> cycle() {
        return cycle;
    }

    // 检查有向图是否含有有向环
    private boolean check() {
        if(hasCycle()) {
            int first = -1, last = -1;
            for(int v : cycle()) {
                if(first == -1)
                    first = v;
                last = v;
            }
            if(first != last)
                return false;
        }
        return true;
    }
}
