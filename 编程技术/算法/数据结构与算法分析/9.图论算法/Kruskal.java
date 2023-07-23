// 贪婪算法计算最小生成树
ArrayList<Edge> Kruskal(List<Edge> edges, int numVertices) {
    Disjsets ds = new Disjsets(numVertices);    // 存储节点的不相交集合
    PriorityQueue<Edge> pq = new PriorityQueue<>(edges);    // 按边的权重存储边的优先队列
    List<Edge> mst = new ArrayList<>();     // 存储最小生成树的边的List

    while (mst.size() != numVertices - 1) {
        Edge e = pq.deleteMin();    // Edge e = (u, v)
        SetType uset = ds.find(e.getu());
        SetType vset = de.find(e.getv());

        if (uset != vset) {
            // Accept the edge
            mst.add(e);
            ds.union(uset, vset);
        }
    }
    return mst;
}