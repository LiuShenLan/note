public class Quick3way {
    // 处理含有大量重复元素的快速排序

    public static void sort(Comparable[] a) {
        // 将a[]按升序排列
        StdRandom.shuffle(a);   // 消除对输入的依赖
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo)
            return ;
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0)
                exch(a, lt++, i++);
            else if(cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }
        // 现在 a[lo...lt-1] < v = a[lt...gt] < a[gt+1...hi]成立
        sort(a, lo, lt - 1); // 将左半部分a[lo...j-1]排序
        sort(a, gt + 1, hi); // 将右半部分a[j+1...hi]排序
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo...i-1], a[i], a[i+1...hi]
        int i = lo;
        int j = hi + 1; // 左右扫描指针
        Comparable v = a[lo];   // 切分元素
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v))
                if(i == hi)
                    break;
            while (less(v, a[--j]))
                if(j == lo)
                    break;
            if(i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j); // 将v = a[j]放入正确的位置
        return j;   // a[lo...j-1] <= a[j] <= a[j+1...hi]达成
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        // 测试数组元素是否有序
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i - 1]))
                return false;
        return true;
    }
}