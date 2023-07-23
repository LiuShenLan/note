public class BreadthFirstPaths {
    // 广度优先搜索查找无向图中的路径

    private static final int INFINITY = Integer.MAX_VALUE;

    private boolean[] marked;   // s-v最短路径是否已知
    private int[] edgeTo;   // s-v路径上的最后一个顶点,该数组为由父链接组成的一棵树
    private int[] distTo;   // s-v最短路径边数
    private final int s;    // 起点

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<>();
        for(int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;   // 标记起点
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();    // 从队列中删去下一顶点
            for(int w : G.adj(v)) {
                if(!marked[w]) {    // 对于每个未被标记的相邻
                    edgeTo[w] = v;  // 保存最短路径的最后一条边
                    marked[w] = true;   // 标记该顶点
                    distTo[w] = distTo[v] + 1;  // 记录最短距离
                    q.enqueue(w);   // 并将它添加到队列中
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for(x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}