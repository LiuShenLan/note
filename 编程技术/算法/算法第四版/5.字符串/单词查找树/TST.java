public class TST<Value> {
    // 基于三向单词查找树的符号表

    private int n;  // 键值对数量
    private Node<Value> root;   // 树的根节点

    private static class Node<Value> {
        private char c; // 字符
        private Node<Value> left, mid, right;   // 左中右子三向单词查找树(左右为兄弟节点,mid为子节点?)
        private Value val;  // 和字符串相关联的值
    }

    // 返回符号表中的键值对
    public int size() {
        return n;
    }

    // 符号表是否含有给定键
    public boolean contains(String key) {
        return get(key) != null;
    }

    // 返回给定键的值
    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if(x == null)
            return null;
        return x.val;
    }

    // 返回给定键相应的子树
    private Node<Value> get(Node<Value> x, String key, int d) {
        if(x == null)
            return null;
        char c = key.charAt(d);
        if(c < x.c)
            return get(x.left, key, d);
        else if(c > x.c)
            return get(x.right, key, d);
        else if(d < key.length() - 1)
            return get(x.mid, key, d+1);
        else
            return x;
    }

    // 向符号表中插入键值对,如果键已经存在于符号表中则覆盖旧值,如果值为null则删除键
    public void put(String key, Value val) {
        if(!contains(key))
            n++;
        else if(val == null)    // 值为null则删除存在的键
            n--;
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if(x == null) {
            x = new Node<>();
            x.c = c;
        }
        if(c < x.c)
            x.left = put(x.left, key, val, d);
        else if(c > x.c)
            x.right = put(x.right, key, val, d);
        else if(d < key.length() - 1)
            x.mid = put(x.mid, key, val, d+1);
        else
            x.val = val;
        return x;
    }

    // 返回符号表中query的前缀中最长的键
    public String longestPrefixOf(String query) {
        if(query.length() == 0)
            return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if(c < x.c)
                x = x.left;
            else if(c > x.c)
                x = x.right;
            else {
                i++;
                if(x.val != null)
                    length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    //返回符号表中的所有的键
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    // 返回符号表中所有以prefix为前缀的键
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        Node<Value> x = get(root, prefix, 0);
        if(x == null)
            return queue;
        if(x.val != null)
            queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    // 收集以x为根节点的子单词查找树中的所有键,并添加到队列queue中
    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
        if(x == null)
            return ;
        collect(x.left, prefix, queue);

        if(x.val != null)
            queue.enqueue(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), queue);

        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    // 返回符号表中所有和pattern匹配的键, "."可以匹配任何字符
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    // 收集以x为根节点的子单词查找树中的和pattern匹配的键("."可以匹配任何字符),并添加到队列queue中
    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if(x == null)
            return;
        char c = pattern.charAt(i);
        if(c == '.' || c < x.c)
            collect(x.left, prefix, i, pattern, queue);
        if(c == '.' || c == x.c) {
            if(i == pattern.length() - 1 && x.val != null)
                queue.enqueue(prefix.toString() + x.c);
            if(i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if(c == '.' || c > x.c)
            collect(x.right, prefix, i, pattern, queue);
    }
}
