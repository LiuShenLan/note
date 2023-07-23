public class MSD {
    // 高位优先的字符串排序

    private static final int BITS_PER_BYTE = 8;
    private static final int BITS_PER_INT = 32; // each Java int is 32 bits
    private static final int R = 256;   // 基数,扩展ASCII字符集
    private static final int CUTOFF = 15;  // 小数组切换为插入排序的阈值

   // 升序排列由扩展ASCII字符集构成的字符串数组
    public static void sort(String[] a) {
        int n = a.length;
        String[] aux = new String[n];   // 数据分类的辅助数组
        sort(a, 0, n-1, 0, aux);
    }

    // 返回字符串的第d个字符,若d为字符串长度则返回-1
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if(d == s.length())
            return -1;
        return s.charAt(d);
    }

    // 以第d个字符为键将a[lo]至a[hi]排序
    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {

        // 小型子数组转换到插入排序
        if(hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return ;
        }

        // 计算频率
        int[] count = new int[R+2];
        for(int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c+2]++;
        }

        // 将频率转换为索引
        for(int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        // 数据分类
        for(int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        // 回写
        for(int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];


        // 递归的以每个字符为键进行排序
        for(int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    // 对前d个字符均相同的字符串a[lo..hi]执行插入排序
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

    // 从第d个字符开始,v是否小于w
    private static boolean less(String v, String w, int d) {
        for(int i = d; i < Math.min(v.length(), w.length()); i++) {
            if(v.charAt(i) < w.charAt(i))
                return true;
            if(v.charAt(i) > w.charAt(i))
                return false;
        }
        return v.length() < w.length();
    }

    /*排列32位整数数组 */

    // 升序排列32位整数数组(假设整数非负)
    public static void sort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        sort(a, 0, n-1, 0, aux);
    }

    // 从第d个字符开始的高位优先排序a[lo]到a[hi]
    private static void sort(int[] a, int lo, int hi, int d, int[] aux) {

        // 小型子数组转换到插入排序
        if(hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return ;
        }

        // 计算频率(要求 R = 256)
        int[] count = new int[R+1];
        int mask = R - 1;   // 0xFF;
        int shift = BITS_PER_INT - BITS_PER_BYTE * d - BITS_PER_BYTE;
        for(int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            count[c + 1]++;
        }

        // 将频率转换为索引
        for(int r = 0; r < R; r++)
            count[r+1] += count[r];

        // 数据分类
        for(int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            aux[count[c]++] = a[i];
        }

        // 回写
        for(int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        // no more bits
        if(d == 4)
            return ;

        // 对每个字符进行递归排序
        if(count[0] > 0)
            sort(a, lo, lo + count[0] - 1, d+1, aux);
        for(int r = 0; r < R; r++)
            if(count[r+1] > count[r])
                sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    // 对前d位均相同的数组a[lo..hi]执行插入排序
    private static void insertion(int[] a, int lo, int hi, int d) {
        for(int i = lo; i <= hi; i++)
            for(int j = i; j > lo && a[j] < a[j-1]; j--)
                exch(a, j, j-1);
    }

    // 交换a[i]和a[j]
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
