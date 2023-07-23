// 计算具有负边值的图的最短路径 O(|E| * |V|)
void WeightedNegative(Vertex s) {
    Queue<Vertex> q = new Queue<Vertex>();
    
    for each Vertex v
        v.dist = INFINITY;
    
    s.dist = 0;
    q.enqueue(s);

    while (!q.isEmpty()) {
        Vertex v = q.dequeue();

        for each Vertex w adjacent to v
            if (v.dist + cvw < w.dist) {
                // Update w
                w.dist = v.dist + cvw;
                w.path = v;
                if (w is not a;ready in q)
                    q.enqueue(w);
            }
    }
}