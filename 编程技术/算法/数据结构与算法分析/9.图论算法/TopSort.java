// 拓扑排序
void TopSort() throws CycleFoundException {
    Queue<Vertex> q = new Queue<Vertex>();
    int counter = 0;

    for each Vertex v
        if (v.indegree == 0)
            q.enqueue(v);
    
    while (!q.isEmpty()) {
        Vertex v = q.dequeue();
        t.topNum = ++counter;   // Assign next number

        for each Vertex w adjacent to v
            if (--w.indegree == 0)
                q.enqueue(w);
    }
    if (counter != NUM_VERTICES)
        throw new CycleFoundException();
}