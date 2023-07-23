// 无权最短路径算法
void Unweighted(Vertex s) {
    Queue<Vertex> q = new Queue<Vertex>();

    for each Vertex v
        v.dist = INFINITY;
    
    s.dist = 0;
    q.enqueue(s);

    while(!q.isEmpty()) {
        Vertex v = q.dequeeu();

        for each Vertex w adjacent to v
            if (w.dist == INFINITY) {
                w.dist = v.dist + 1;
                w.path = v;
                q.enqueue(w);
            }
    }
}