public class MaxPQ<Key extends Comparable<Key>> {
    // 最大堆，每个节点>=子节点

    private Key[] pq;   // 基于堆的完全二叉树
    private int N = 0;  // 存储于pq[1...N]中,pq[0]没有使用

    public static void sort(Comparable[] a) {
        // 将a[]按升序排列
        int N = a.length;
        for(int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key deleteMax() {
        Key max = pq[1];    // 从跟节点得到最大元素
        exch(1, N--);       // 将其和最后一个节点交换
        pq[N+1] = null;     // 防止越界
        sink(1);            // 恢复堆的有序性
        return max;
    }

    // 由下至上的堆有序化(上浮)
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    // 由上至下的堆有序化(下沉)
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if(j < N && less(j, j + 1))
                j++;
            if(!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private static boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        // 测试数组元素是否有序
        for(int i = 1; i < a.length; i++)
            if(less(i, i - 1))
                return false;
        return true;
    }

    // 排序用
    private void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if(j < n && less(a, j, j + 1))
                j++;
            if(!less(a, k, j))
                break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}