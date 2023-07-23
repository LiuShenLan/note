public class Quick3string {
    // 三向字符串快速排序

    private static final int CUTOFF =  15;   // 转换为插入排序的阈值

    // 升序排列字符串数组
    public static void sort(String[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1, 0);
        assert isSorted(a);
    }

    // 返回字符串s的第d个字符,如果d=s.length则返回-1
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if(d == s.length())
            return -1;
        return s.charAt(d);
    }

    // 从第d个字符开始三向字符串快速排序a[lo..hi]
    private static void sort(String[] a, int lo, int hi, int d) {

        // 小型子数组切换到插入排序
        if(hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return ;
        }

        int lt = lo, gt = hi;   // v = a[lt..gt]
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if(t < v)
                exch(a, lt++, i++);
            else if(t > v)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1, d);
        if(v >= 0)
            sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    // 对前d个字符相同的字符串数组a[lo...hi]进行插入排序
    private static void insertion(String[] a, int lo, int hi, int d) {
        for(int i = lo; i <= hi; i++)
            for(int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // 交换a[i]和a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 从第d个字符开始,v是否小于d
    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for(int i = d; i < Math.min(v.length(), w.length()); i++) {
            if(v.charAt(i) < w.charAt(i))
                return true;
            if(v.charAt(i) > w.charAt(i))
                return false;
        }
        return v.length() < w.length();
    }

    // 数组是否有序
    private static boolean isSorted(String[] a) {
        for(int i = 1; i < a.length; i++)
            if(a[i].compareTo(a[i-1]) < 0)
                return false;
        return true;
    }

}
