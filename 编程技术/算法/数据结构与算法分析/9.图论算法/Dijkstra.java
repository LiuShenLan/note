// 贪婪算法计算赋权图的最短路径 O(|E| + |V|^2)
void Dijkstra(Vertex s) {
    for each Vertex v {
        v.dist = INFINITY;
        v.konwn = false;
    }

    s.dist = 0;

    while (there is an unknown distance vertex) {
        Vertex v = smallest unknown distance vertex;

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


class Vertex {
    public List adj;    // Adjacency list
    public boolean known;
    public DistType dist;   // DistType is probably int
    public Vertex path; // Other fields and methods as needed
}

/**
 * Print shortest path to v after dijkstra has run.
 * Assume that the path exists.
 */
void printPath(Vertex v) {
    if (v.path != null) {
        printPath(v.path);
        System.out.print(" to ");
    }
    System.out.print(v);
}