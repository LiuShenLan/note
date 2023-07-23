public class BinarySearchST<Key extends Comparable<Key>, Value> {
    // 二分查找(基于有序数组)

    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if(isEmpty())
            return null;

        int i = rank(key);

        if(i < N && keys[i].compareTo(key) == 0)
            return vals[i];

        return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
                hi = mid - 1;
            else if(cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public void put(Key key, Value val)  {
        // 查找键，找到则更新值，否则创建新的元素
        if(val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return ;
        }

        for(int j = N; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if(isEmpty())
            return ;

        int i = rank(key);

        if(i == N || keys[i].compareTo(key) != 0)
            return;

        for(int j = i; j < N - 1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        N--;
        keys[N] = null;  // 避免游离
        vals[N] = null;
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if(i < N && key.compareTo(keys[i]) == 0)
            return keys[i];
        if(i == 0)
            return null;
        else return keys[i-1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if(i == N)
            return null;
        else
            return keys[i];
    }

    public int size(Key lo, Key hi) {
        if(lo.compareTo(hi) > 0)
            return 0;
        if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();

        if(lo.compareTo(hi) > 0)
            return queue;

        for(int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if(contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    private boolean isSorted() {
        for(int i = 1; i < size(); i++)
            if(keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    private boolean rankCheck() {
        for(int i = 0; i < size(); i++)
            if(i != rank(select(i)))
                return false;
        for(int i = 0; i < size(); i++)
            if(keys[i].compareTo(select(rank(keys[i]))) != 0)
                return false;
        return true;
    }

    /*操作成本
    方法    运行所需时间的增长数量级
    put()       N
    get()       logN
    delete()    N
    contains()  logN
    size()      1
    min()       1
    max()       1
    floor()     logN
    ceiling()   logN
    rank()      logN
    select()    1
    deleteMin() N
    deleteMax() 1
    */
}
