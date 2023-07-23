public class FlowNetwork {
    // 流量网络

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;    // 顶点数量
    private int E;  // 边的数量
    private Bag<FlowEdge>[] adj;    // 邻接链表

    // 初始化一个含有v个顶点0条边的空的流量网络
    public FlowNetwork(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    // 随机初始化一个含有v个顶点e条边的流量网络,容量为0~99,流量为0
    public FlowNetwork(int V, int E) {
        this(V);
        for(int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double capacity = StdRandom.uniform(100);
            addEdge(new FlowEdge(v, w, capacity));
        }
    }

    // 从输入流初始化一个流量网络,格式为顶点数V,边数E,然后是E对顶点和容量
    public FlowNetwork(In in) {
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double capacity = in.readDouble();
            addEdge(new FlowEdge(v, w, capacity));
        }
    }

    // 返回有向图中的顶点数
    public int V() {
        return V;
    }

    // 返回有向图中的边数
    public int E() {
        return E;
    }

    // 添加边到网络中
    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    // 返回与顶点v相连接的边(包括顶点v上的自环)
    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    // 返回所有的边(不包括自环)
    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> list = new Bag<>();
        for(int v = 0; v < V; v++)
            for(FlowEdge e : adj(v)) {
                if(e.to() != v)
                    list.add(e);
            }
        return list;
    }

    // 返回流量网络的字符串表示
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for(int v = 0; v < V; v++) {
            s.append(v + ":  ");
            for(FlowEdge e : adj[v])
                if(e.to() != v)
                    s.append(e + "  ");
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
