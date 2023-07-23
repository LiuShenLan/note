public class DirectedEdge {
    // 加权有向边的数据类型

    private final int v;    // 边的起点
    private final int w;    // 边的终点
    private final double weight;    // 边的权重

    // 使用给定的权重初始化一条从v指向w的加权有向边
    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 指出这条边的顶点
    public int from() {
        return v;
    }

    // 这条边指向的顶点
    public int to() {
        return w;
    }

    // 有向边的权重
    public double weight() {
        return weight;
    }

    // 有向边的字符串表示
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
