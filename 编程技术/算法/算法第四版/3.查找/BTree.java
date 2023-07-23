public class BTree<Key extends Comparable<Key>, Value>  {
    // 2-3树数据结构的扩展,关键的不同在于不会将数据保存在树中
    // 而是构造一棵由键的副本组成的树,每个副本都关联着一条链接

    // 每个B树节点的子节点数最多为M-1,最少为M/2(根节点无最少限制),M为大于等于2的偶数
    private static final int M = 4;

    private Node root;  // B树根节点
    private int height; // B树高度
    private int n;  // B树中的键值对数目

    // B树节点数据类型
    private static final class Node {
        private int m;  // 子节点数目
        private Entry[] children = new Entry[M];    // 子节点数组

        // 创建一个有k个子节点的数组
        private Node(int k) {
            m = k;
        }
    }

    // 内部节点:只使用key和next
    // 外部节点:只使用key和value
    private static class Entry {
        private Comparable key; // 键
        private Object val; // 值
        private Node next;  // node子节点所对应的节点
        public Entry(Comparable key, Object val, Node next) {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    // 初始化一棵空的B树
    public BTree() {
        root = new Node(0);
    }

    // 返回符号表是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 返回符号表中键值对的数目
    public int size() {
        return n;
    }

    // 返回B树的高度(用于debug)
    public int height() {
        return height;
    }

    // 返回与给定键相关联的值
    public Value get(Key key) {
        return search(root, key, height);
    }

    // 在以x为根节点的子树中查找键key对应的值
    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;

        // 外部节点
        if(ht == 0) {   // 外部节点
            for(int j = 0; j < x.m; j++) {  // 遍历子节点
                if(eq(key, children[j].key))
                    return (Value) children[j].val;
            }
        } else {    // 内部节点
            for(int j = 0; j < x.m; j++) {  // 遍历子节点
                if(j+1 == x.m || less(key, children[j+1].key))  // 没有更多子节点或找到适当范围
                    return search(children[j].next, key, ht-1);
            }
        }
        return null;    // 未命中查找
    }

    // 向符号表中插入键值对,如果键已存在则用给定值覆盖旧值,如果给定值为null则删除键值对
    public void put(Key key, Value val) {
        Node u = insert(root, key, val, height);
        n++;
        if(u == null)
            return;

        // 需要分裂根节点
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    // 向根节点为h的子树中插入键值对
    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);

        if(ht == 0) {   // 外部节点
            for(j = 0; j < h.m; j++) {
                if(less(key, h.children[j].key))
                    break;  // j为值大于key的最左侧的键
            }
        } else {    // 内部节点
            for(j = 0; j < h.m; j++) {
                if((j+1 == h.m) || less(key, h.children[j+1].key)) {    // 没有更多子节点或找到适当范围
                    Node u = insert(h.children[j++].next, key, val, ht-1);
                    if(u == null)
                        return null;
                    t.key = u.children[0].key;
                    t.val = null;
                    t.next = u;
                    break;
                }
            }
        }

        for(int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if(h.m < M)
            return null;
        else    // 子节点数超出上限M-1,将该节点二分裂
            return split(h);
    }

    // 将节点二分裂
    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M/2;
        for(int j = 0; j < M/2; j++)
            t.children[j] = h.children[M/2+j];
        return t;
    }

    // 返回B树的字符串表示(用于debug)
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    // 返回以h为根节点,高度为ht的子树的字符串表示
    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if(ht == 0) {   // 外部节点
            for(int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {    // 内部节点
            for(int j = 0; j < h.m; j++) {
                if(j > 0)
                    s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }

    // 返回k1是否小于k2
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    // 返回k1与k2是否相等
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
}
