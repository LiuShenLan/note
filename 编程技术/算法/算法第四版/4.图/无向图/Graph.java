public class Graph {
    // 无向图数据结构

    private static final String NEWLINE = System.getProperty("line.separator"); // 行分隔符

    private final int V;    // 顶点数目
    private int E;  // 边的数目
    private Bag<Integer>[] adj; // 邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // 创建邻接表
        for(int v = 0; v < V; v++) {   // 将所有链表初始化为空
            adj[v] = new Bag<>();
        }
    }

    // 初始化为输入G的deep copy
    public Graph(Graph G) {
        this.V = G.V();
        this.E = G.E();

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
        adj[w].add(v);
        E++;
    }

    // 返回邻接表
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // 返回顶点的度数(依附于顶点的边的总数)
    public int degree(int v) {
        return adj[v].size();
    }

    // 返回表示图的字符串
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for(int v = 0; v < V; v++) {
            s.append(v + ": ");
            for(int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
