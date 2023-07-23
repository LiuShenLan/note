public class DirectedDFS {
	// 有向图的可达性API(深度优先搜索)

	private boolean[] marked;   // 是否存在路径s-v
	private int count;  // 从起点s可达的顶点数

	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	// 多个起点的构造函数
	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for(int v : sources) {
			if(!marked[v])
				dfs(G, v);
		}
	}

	// 深度优先搜索
	private void dfs(Digraph G, int v) {
		count++;
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}

	// 返回从起点是否可到达顶点v
	public boolean marked(int v) {
		return marked[v];
	}

	// 返回从起点可以到达的顶点数
	public int count() {
		return count;
	}
}
