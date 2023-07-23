public class Huffman {
    // 霍夫曼编码
    // 为输入中的定长模式产生一种变长的编码编译表

    private static final int R = 256;   // 扩展ASCII字母表大小

    // 霍夫曼单词查找树中的结点
    private static class Node implements Comparable<Node> {
        private final char ch;  // 叶子结点中需要被编码的字符,内部结点不会使用该变量
        private final int freq; // 以它为根节点的子树中的所有字符出现的频率,展开过程不会使用该变量
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // 该结点是否为叶子结点
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // 基于频率的比较
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    // 从标准输入读取8位字节序列,使用带8位字母的霍夫曼编码压缩它们,并将结果写入标准输出流
    public static void compress() {
        // 读取输入
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // 统计频率
        int[] freq = new int[R];
        for(int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // 构造霍夫曼编码树
        Node root = buildTrie(freq);

        // (递归地)构造编译表
        String[] st = new String[R];
        buildCode(st, root, "");

        // (递归地)打印解码用的单词查找树
        writeTrie(root);

        // 打印原始未压缩消息中的字节数
        BinaryStdOut.write(input.length);

        // 使用霍夫曼编码处理输入
        for(int i = 0; i < input.length; i++) {
            String code = st[input[i]]; // 向字符串中添加字节编码

            // 打印输出字符串
            for(int j = 0; j < code.length(); j++) {
                if(code.charAt(j) == '0')
                    BinaryStdOut.write(false);
                else if(code.charAt(j) == '1')
                    BinaryStdOut.write(true);
            }
        }
        // 关闭输出流
        BinaryStdOut.close();
    }

    // 根据给定频率构建霍夫曼单词编码树
    private static Node buildTrie(int[] freq) {

        // 使用多棵单节点树初始化优先队列
        MinPQ<Node> pq = new MinPQ<>();
        for(char c = 0; c < R; c++)
            if(freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null));

        // 合并两棵频率最小的树
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    // 输出单词查找树的比特字符串
    private static void writeTrie(Node x) {
        if(x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
            return ;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    // 使用单词查找树构建编译表
    private static void buildCode(String[] st, Node x, String s) {
        // st:结果写入字符串列表    x:递归节点  s:从根节点到节点x的比特码
        if(!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        } else
            st[x.ch] = s;
    }

    // 从标准输入读取表示霍夫曼压缩信息的比特流并展开,将结果写入标准输出
    public static void expand() {
        Node root = readTrie(); // 从输入流中读取霍夫曼单词查找树

        int length = BinaryStdIn.readInt(); // 待写入的字节数

        for(int i = 0; i < length; i++) {  // 展开第i个编码所对应的字母
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();    // 读取下一位比特位
                if(bit) // bit为1
                    x = x.right;
                else    // bit为0
                    x = x.left;
            }
            BinaryStdOut.write(x.ch, 8);
        }
        BinaryStdOut.close();
    }

    // 从比特流的前序表示中重建单词查找树
    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if(isLeaf)
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        else
            return new Node('\0', -1, readTrie(), readTrie());
    }
}
