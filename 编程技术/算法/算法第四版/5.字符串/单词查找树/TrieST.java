public class TrieST<Value> {
    // 基于单词查找树的符号表

    private static final int R = 256;   // 基数,扩展ASCII字母表

    private Node root;  // 单词查找树的根节点
    private int n;  // 单词查找树中键的数量

    // R向单词查找树节点
    private static class Node {
        private Object val; // 值
        private Node[] next = new Node[R];  // 链接,按照数组顺序对应字母表中的字符
    }

    // 返回给定键的值
    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x == null)
            return null;
        return (Value) x.val;
    }

    // 符号表是否含有给定键
    public boolean contains(String key) {
        return get(key) != null;
    }

    // 返回以x作为根节点的子单词查找树中键为key的节点
    private Node get(Node x, String key, int d) {
        if(x == null)
            return null;
        if(d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    // 向符号表中插入键值对,如果键已经存在于符号表中则覆盖旧值,如果值为null则删除键
    public void put(String key, Value val) {
        if(val == null)
            delete(key);
        else
            root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if(x == null)
            x = new Node();
        if(d == key.length()) {
            if(x.val == null)
                n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // 找到第d个字符所对应的子单词查找树
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    // 返回符号表中的键值对
    public int size() {
        return n;
    }

    // 返回符号表是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    //返回符号表中的所有的键
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    // 返回符号表中所有以prefix为前缀的键
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<>();
        Node x = get(root, prefix, 0);  // 确定符号表中前缀所对应的节点
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    // 收集以x为根节点的子单词查找树中的所有键,并添加到队列result中
    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if(x == null)
            return ;
        if(x.val != null)
            results.enqueue(prefix.toString());
        for(char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // 返回符号表中所有和pattern匹配的键, "."可以匹配任何字符
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    // 收集以x为根节点的子单词查找树中的和pattern匹配的键("."可以匹配任何字符),并添加到队列result中
    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if(x == null)
            return;
        int d = prefix.length();
        if(d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if(d == pattern.length())
            return ;

        char c = pattern.charAt(d);
        if(c == '.') {
            for(char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // 返回符号表中query的前缀中最长的键
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if(length == -1)
            return null;
        else
            return query.substring(0, length);
    }

    // 返回以x为根节点的query的最长前缀的键的长度
    // 假设前d个字符已经匹配,并且已经找到长度为length的已经匹配的前缀
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if(x == null)
            return length;
        if(x.val != null)
            length = d;
        if(d == query.length())
            return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    // 删除键值对
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    // 递归的在以x为根节点的子树上删除key从第d个字符开始的键
    private Node delete(Node x, String key, int d) {
        if(x == null)
            return null;

        // 递归删除
        if(d == key.length()) { // 查找到了该键,值设为null
            if(x.val != null)
                n--;
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // 如果以x为根节点的子树完全为空则删除
        if(x.val != null)
            return x;
        for(int c = 0; c < R; c++)
            if(x.next[c] != null)
                return x;
        return null;
    }
}
