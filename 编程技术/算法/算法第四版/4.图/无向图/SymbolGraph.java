public class SymbolGraph {
    // 符号图:用符号作为顶点名的无向图

    private ST<String, Integer> st; // 符号表:顶点名 -> 顶点索引
    private String[] keys;  // 数组:顶点索引 -> 顶点名
    private Graph graph;    // 无向图

    public SymbolGraph(String filename, String delimiter) {
        st = new ST<>();

        // 第一次遍历通过读取字符串以将顶点名与顶点索引相关联
        In in = new In(filename);
        while(!in.isEmpty()) { // 构造索引
            String[] a = in.readLine().split(delimiter);

            for(int i = 0; i < a.length; i++)  // 为每个不同的字符串关联一个索引
                if(!st.contains(a[i]))
                    st.put(a[i], st.size());
        }

        // 反向索引以在数组中获得字符串键
        keys = new String[st.size()];   // 用来获得顶点名的反向索引是一个数组
        for(String name : st.keys())
            keys[st.get(name)] = name;

        // 第二次遍历通过将每条边上的第一个顶点连接到其他顶点来构建无向图
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    // 返回图是否含有顶点名s
    public boolean contains(String s) {
        return st.contains(s);
    }

    // 返回顶点名s的索引
    public int indexOf(String s) {
        return st.get(s);
    }

    // 返回顶点索引v对应的顶点名
    public String nameOf(int v) {
        return keys[v];
    }

    // 返回符号图对应的无向图
    public Graph graph() {
        return graph;
    }
}
