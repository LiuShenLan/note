public class DepthFirstOrder {
    // 有向图中基于深度优先搜索的顶点排序

    private boolean[] marked;   // 深度优先搜索中顶点v是否被标记
    private int[] pre;  // 顶点v的前序排列编号
    private int[] post; // 顶点v的后序排列编号
    private Queue<Integer> preorder;    // 所有顶点的前序排列
    private Queue<Integer> postorder;   // 所有顶点的后续排列
    private int preCounter; // 前序排列计数器
    private int postCounter;    // 后序排列计数器

    public DepthFirstOrder(Digraph G) {
        pre = new int[G.V()];
        post = new int[G.V()];

        postorder = new Queue<>();
        preorder = new Queue<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++)
            if(!marked[v])
                dfs(G, v);

        assert check();
    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre = new int[G.V()];
        post = new int[G.V()];

        postorder = new Queue<>();
        preorder  = new Queue<>();
        reversePost = new Stack<>();

        marked    = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++)
            if(!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        pre[v] = preCounter++;
        preorder.enqueue(v);

        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }

        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;

        pre[v] = preCounter++;
        preorder.enqueue(v);

        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.enqueue(v);
        post[v] = postCounter++;
    }

    // 返回顶点v的前序排列编号
    public int pre(int v) {
        return pre[v];
    }

    // 返回顶点v的后序排列编号
    public int post(int v) {
        return post[v];
    }

    // 返回所有顶点的前序排列
    public Iterable<Integer> pre() {
        return preorder;
    }

    // 返回所有顶点的后续排列
    public Iterable<Integer> post() {
        return postorder;
    }



    // 返回所有顶点的逆后序排列
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<>();
        for(int v : postorder)
            reverse.push(v);
        return reverse;
    }

    // 检查前后序排列编号与前后序排列定点顺序一致
    private boolean check() {
        // 前序排列
        int r = 0;
        for(int v : pre()) {
            if(pre(v) != r)
                return false;
            r++;
        }

        // 后序排列
        r = 0;
        for(int v : post()) {
            if(post(v) != r)
                return false;
            r++;
        }
        return true;
    }
}
