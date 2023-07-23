public class SeparateChainingHashST<Key, Value> {
    // 基于拉链法的散列表

    private static final int INIT_CAPACITY = 4;

    private int n;  // 键值对总数
    private int m;  // 散列表的大小
    private SequentialSearchST<Key, Value>[] st;    // 存放链表对象的数组

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        // 创建m条链表
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for(int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<>();
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for(int i = 0; i < m; i++) {
            for(Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m-1);

        /* 原实现
        return (key.hashCode() & 0x7fffffff) % m;
        */
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
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value val) {
        if(val == null) {
            delete(key);
            return;
        }

        // double table size ifaverage length of list >= 10
        if(n >= 10 * m)
            resize(2*m);

        int i = hash(key);
        if(!st[i].contains(key))
            n++;
        st[i].put(key, val);
    }

    public void delete(Key key) {
        int i = hash(key);
        if(st[i].contains(key))
            n--;
        st[i].delete(key);

        // halve table size ifaverage length of list <= 2
        if(m > INIT_CAPACITY && n <= 2 * m)
            resize(m/2);
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for(int i = 0; i < m; i++) {
            for(Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }
}