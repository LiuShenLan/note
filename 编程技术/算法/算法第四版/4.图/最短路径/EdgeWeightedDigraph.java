public class EdgeWeightedDigraph {
    // 加权有向图的数据类型

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;    // 顶点总数
    private int E;  // 边的总数
    private Bag<DirectedEdge>[] adj;    // 邻接表
    private int[] indegree; // 顶点v的入度

    // 初始化含有V个顶点的空加权有向图
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    // 随机初始化含有V个顶点E条边的加权有向图
    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        for(int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    // 从输入流中读取图
    public EdgeWeightedDigraph(In in) {
        try {
            this.V = in.readInt();
            indegree = new int[V];
            adj = (Bag<DirectedEdge>[]) new Bag[V];
            for(int v = 0; v < V; v++)
                adj[v] = new Bag<>();

            int E = in.readInt();
            for(int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                addEdge(new DirectedEdge(v, w, weight));
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }

    // 初始化加权有向图G的deep copy
    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        for(int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);

        for(int v = 0; v < G.V(); v++) {
            // 反转邻接表使得与G顺序相同
            Stack<DirectedEdge> reverse = new Stack<>();
            for(DirectedEdge e : G.adj[v])
                reverse.push(e);
            for(DirectedEdge e : reverse)
                adj[v].add(e);
        }
    }

    // 顶点数
    public int V() {
        return V;
    }

    // 边数
    public int E() {
        return E;
    }

    // 在加权有向图中添加一条边
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    // 返回从v指出的所有边
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // 顶点v的出度(从顶点v指出的边的数目)
    public int outdegree(int v) {
        return adj[v].size();
    }

    // 顶点v的入度(指向顶点v的边的数目)
    public int indegree(int v) {
        return indegree[v];
    }

    // 加权有向图中的所有的边
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<>();
        for(int v = 0; v < V; v++) {
            for(DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    // 返回加权有向图的字符串表示
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for(int v = 0; v < V; v++) {
            s.append(v + ": ");
            for(DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
