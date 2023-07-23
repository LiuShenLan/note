public class Digraph {
    // 有向图数据结构

    private final int V;    // 顶点数目
    private int E;  // 边的数目
    private Bag<Integer>[] adj; // 邻接表
    private int[] indegree; // 顶点v的入度

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    // 初始化为输入G的deep copy
    public Digraph(Digraph G) {
        this.V = G.V();
        this.E = G.E();

        // 更新入度
        indegree = new int[V];
        for(int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);

        // 更新邻接表
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }

        for(int v = 0; v < G.V(); v++) {
            // 反转使得邻接表与原图顺序相同
            Stack<Integer> reverse = new Stack<>();
            for(int w : G.adj[v]) {
                reverse.push(w);
            }
            for(int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    // 返回图的顶点数
    public int V() {
        return V;
    }

    // 返回图的边数
    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    // 返回邻接表
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // 返回顶点v的出度
    public int outdegree(int v) {
        return adj[v].size();
    }

    // 返回顶点v的入度
    public int indegree(int v) {
        return indegree[v];
    }

    // 返回反转后的有向图
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for(int v = 0; v < V; v++) {
            for(int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

}
