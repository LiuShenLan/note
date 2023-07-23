public class SequentialSearchST<Key, Value> {
    // 顺序查找(基于无序链表)

    private int n;  // 键值对的数目
    private Node first; // 链表首结点

    private class Node {
        // 链表结点的定义
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        // 查找给定的键，返回相关联的值
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key))
                return x.val;   // 命中
        return null;    // 未命中
    }

    public void put(Key key, Value val) {
        if(val == null) {
            delete(key);
            return;
        }

        // 查找给定的键，找到则更新其值，否则在表中新建结点
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key)) {
                x.val = val;    // 命中，更新
                return ;
            }
        first = new Node(key, val, first);  // 未命中，新建结点
        n++;
    }

    public void delete(Key key) {
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if(x == null)
            return null;

        if(key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for(Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
}
