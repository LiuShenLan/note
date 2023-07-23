// 深度优先搜索模板
void dfs(Vertex x) {
    v.visited = true;
    for each Vertex w adjacent to v
        if (!w.visited)
            dfs(w);
}