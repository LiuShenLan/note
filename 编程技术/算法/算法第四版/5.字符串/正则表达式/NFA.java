public class NFA {
    // 非确定有限状态自动机

    private Digraph graph;  // ε-转换有向图
    private String regexp;  // 正则表达式
    private final int m;    // 状态数量(正则表达式中字符数量)

    // 根据给定的正则表达式构造NFA
    public NFA(String regexp) {
        this.regexp = regexp;
        m = regexp.length();
        Stack<Integer> ops = new Stack<>(); // 使用一个栈来记录所有的左括号和或运算符
        graph = new Digraph(m+1);

        for(int i = 0; i < m; i++) {
            int lp = i;

            // 处理左右括号或或运算符
            if(regexp.charAt(i) == '(' || regexp.charAt(i) == '|')
                ops.push(i);    // 将左括号和或运算符的索引压入栈中
            else if(regexp.charAt(i) == ')') {  // 右括号
                int or = ops.pop(); // 栈顶元素索引

                if(regexp.charAt(or) == '|') {  // 栈顶为或运算符
                    lp = ops.pop(); // lp为左括号索引
                    graph.addEdge(lp, or+1);    // 左括号指向或运算符之后正则表达式的第一个字符
                    graph.addEdge(or, i);   // 或运算符指向右括号
                } else if(regexp.charAt(or) == '(') // 栈顶为左括号
                    lp = or;
                else    // 不是左括号或或运算符,则程序出错
                    assert false;
            }

            // 闭包运算符(查看下一个字符)
            if(i < m-1 && regexp.charAt(i+1) == '*') {
                graph.addEdge(lp, i+1); // 左括号或单字符指向*
                graph.addEdge(i+1, lp); // *指向左括号或单字符
            }

            // 添加括号或或运算符或闭包运算指向下一个字符的ε-转换
            if(regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')')
                graph.addEdge(i, i+1);
        }

        if(ops.size() != 0) // 处理结束后栈中还剩余左括号或或运算符
            throw new IllegalArgumentException("Invalid regular expression");
    }

    // NFA能否识别文本txt
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(graph, 0);    // 从起点开始通过ε转换可以到达的顶点(有向图的可达性API(深度优先搜索))
        Bag<Integer> pc = new Bag<>();  // 从起点可以到达的顶点
        for(int v = 0; v < graph.V(); v++)
            if(dfs.marked(v))  // 从起点可以到达顶点v
                pc.add(v);

        // 计算txt[i+1]可能到达的所有NFA状态
        for(int i = 0; i < txt.length(); i++) {
            // 遍历文本中的每一个字符
            Bag<Integer> match = new Bag<>();   // 可以到达的顶点
            for(int v : pc) {  // 遍历所有可以从起点到达的顶点
                if(v == m)
                    continue;
                if((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.')
                    match.add(v+1); // 当前顶点的下一顶点可以到达
            }
            dfs = new DirectedDFS(graph, match);    // 以可以到达的顶点集为起点
            pc = new Bag<>();   // 通过ε转换可以到达的顶点
            for(int v = 0; v < graph.V(); v++)
                if(dfs.marked(v))
                    pc.add(v);

            // 如果没有状态可达则直接返回,优化效率
            if(pc.size() == 0)
                return false;
        }

        // 检查可接受状态
        for(int v : pc)
            if(v == m)
                return true;
        return false;
    }
}
