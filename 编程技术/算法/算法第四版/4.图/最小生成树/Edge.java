public class Edge implements Comparable<Edge> {
    // 带权重的边的数据类型

    private final int v;    // 顶点之一
    private final int w;    // 另一个顶点
    private final double weight;    // 边的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 返回边的权重
    public double weight() {
        return weight;
    }

    // 返回边的任一顶点
    public int either() {
        return v;
    }

    // 返回边的给定顶点的另一顶点
    public int other(int vertex) {
        if(vertex == v)
            return w;
        else if(vertex == w)
            return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    // 比较两条边的权重
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    // 返回边的字符串表示
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
