public class FlowEdge {
    // 流量网络中的边

    private static final double FLOATING_POINT_EPSILON = 1E-10; // 处理浮点舍入错误

    private final int v;    // 边的起点
    private final int w;    // 边的终点
    private final double capacity;  // 容量
    private double flow;    // 流量

    // 根据给定容量初始化一条从v指向w的流量为0的边
    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    // 根据给定容量和流量初始化一条从v指向w的边
    public FlowEdge(int v, int w, double capacity, double flow) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = flow;
    }

    // 从另一条边初始化一条边
    public FlowEdge(FlowEdge e) {
        this.v = e.v;
        this.w = e.w;
        this.capacity = e.capacity;
        this.flow = e.flow;
    }

    // 返回边的起点
    public int from() {
        return v;
    }

    // 返回边的终点
    public int to() {
        return w;
    }

    // 返回边的容量
    public double capacity() {
        return capacity;
    }

    // 返回边的流量
    public double flow() {
        return flow;
    }

    // 返回边的与给定顶点不同的顶点(若为自环则返回相同的顶点)
    public int other(int vertex) {
        if(vertex == v)
            return w;
        else if(vertex == w)
            return v;
    }

    // 返回边在指向给定顶点的剩余容量
    public double residualCapacityTo(int vertex) {
        if(vertex == v)
            return flow;    // 逆向边
        else if(vertex == w)
            return capacity - flow; // 前向边
    }

    // 在指向给定顶点的边上增加流量
    // 如果给定顶点是起点,在边上减少流量delta,否则减少流量delta
    public void addResidualFlowTo(int vertex, double delta) {
        if(vertex == v)
            flow -= delta;  // 逆向边
        else if(vertex == w)
            flow += delta;  // 前向边

        // 将浮点数舍入为0或浮点精度范围内
        if(Math.abs(flow) <= FLOATING_POINT_EPSILON)
            flow = 0;
        if(Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
            flow = capacity;
    }

    // 返回边的字符串表示
    public String toString() {
        return v + "->" + w + " " + flow + "/" + capacity;
    }
}
