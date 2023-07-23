// 贪婪算法计算最小生成树
void Prim(Vertex s) {
    for each Vertex v {
        v.dist = INFINITY;
        v.konwn = false;
    }

    s.dist = 0;

    while (there is an unknown distance vertex) {
        Vertex v = smallest unknown distance vertex;
        add v in minimum spanning tree;

        v.known = true;

        for each Vertex w adjacent to v
            if (!w.konwn) {
                DistType cvw = cost of edge from v to w;

                if (v.dist + cvw < w.dist) {
                    // Update w
                    decrease(w.dist to v.dist +cvw);
                    w.path = v;
                }
            }
    }
}
