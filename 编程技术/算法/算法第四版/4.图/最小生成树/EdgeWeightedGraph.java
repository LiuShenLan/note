public class EdgeWeightedGraph {
    // 加权无向图的数据结构

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;    // 顶点总数
    private int E;  // 边的总数
    private Bag<Edge>[] adj;    // 邻接表

    // 初始化一个含有V个顶点的空加权无向图
    public EdgeWeightedGraph(int V) {
        if(V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    // 初始化一个有V个顶点E条边的随机加权无向图
    public EdgeWeightedGraph(int V, int E) {
        this(V);
        for(int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    // 从输入流中初始化一个加权无向图
    public EdgeWeightedGraph(In in) {
        try {
            V = in.readInt();
            adj = (Bag<Edge>[]) new Bag[V];
            for(int v = 0; v < V; v++) {
                adj[v] = new Bag<>();
            }

            int E = in.readInt();
            for(int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                Edge e = new Edge(v, w, weight);
                addEdge(e);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
        }

    }

    // 初始化图G的deep copy
    public EdgeWeightedGraph(EdgeWeightedGraph G) {
        this(G.V());
        this.E = G.E();
        for(int v = 0; v < G.V(); v++) {
            // 反转使得邻接表与原顺序相同
            Stack<Edge> reverse = new Stack<>();
            for(Edge e : G.adj[v])
                reverse.push(e);
            for(Edge e : reverse)
                adj[v].add(e);
        }
    }


    // 返回加权无向图中顶点数目
    public int V() {
        return V;
    }

    // 返回加权无向图中边的数目
    public int E() {
        return E;
    }

    // 添加边
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    // 返回顶点v的邻接表
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    // 返回顶点v的度数
    public int degree(int v) {
        return adj[v].size();
    }

    // 返回加权无向图中的所有边
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for(int v = 0; v < V; v++) {
            int selfLoops = 0;
            for(Edge e : adj(v)) {
                if(e.other(v) > v) {
                    list.add(e);
                } else if(e.other(v) == v) {   // 自环只添加一次
                    if(selfLoops % 2 == 0)
                        list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    // 返回加权无向图的字符串表示
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for(int v = 0; v < V; v++) {
            s.append(v + ": ");
            for(Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
