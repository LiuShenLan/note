public class Shell {
    /*
    希尔排序的思想是使数组中任意间隔为h的元素都是有序的，这样的数组被称为h有序数组。
    即一个h有序数组就是h个互相独立的有序数组编织在一起组成的一个数组
    在进行排序时，如果h很大，我们就能将元素移到很远的地方，为实现更小的h有序创造方便
    对于任意以1结尾的h序列，我们都能够将数组排序
    */
    public static void sort(Comparable[] a) {
        // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;  // 1, 4, 13, 40, 121, 364, 1093...
        while (h >= 1) {
            // 将数组变为h有序
            for(int i = h; i < N; i++) {
                // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]...之中
                for(int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h / 3;
        }
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