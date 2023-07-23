public class SuffixArrayX {
    // 后缀数组(优化版)
    private static final int CUTOFF = 5;    // 切换为插入排序的阈值(0~12的任意值)

    private final char[] text;  // 输入字符串数组
    private final int[] index;  // index[i] = j 表示 text.substring(j) 是第i小的后缀
    private final int n;    // 输入字符串中的字符树

    // 从给定输入字符串初始化后缀数组
    public SuffixArrayX(String text) {
        n = text.length();
        text = text + '\0';
        this.text = text.toCharArray();
        this.index = new int[n];
        for(int i = 0; i < n; i++)
            index[i] = i;
        sort(0, n-1, 0);
    }

    // 从第d个字符开始将lo~hi进行三向字符串快速排序
    private void sort(int lo, int hi, int d) {
        // 小子数组抓换为插入排序
        if(hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = text[index[lo] + d];
        int i = lo + 1;
        while (i <= gt) {
            char t = text[index[i] + d];
            if(t < v)
                exch(lt++, i++);
            else if(t > v)
                exch(i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(lo, lt-1, d);
        if(v > 0)
            sort(lt, gt, d+1);
        sort(gt+1, hi, d);
    }

    // 从第d个字符开始将a[lo...hi]进行插入排序
    private void insertion(int lo, int hi, int d) {
        for(int i = lo; i <= hi; i++)
            for(int j = i; j > lo && less(index[j], index[j-1], d); j--)
                exch(j, j-1);   // 交换 index[i] 和 index[j]
    }

    // 返回是否 text[i+d...n] < text[j+d...n]
    private boolean less(int i, int j, int d) {
        if(i == j)
            return false;
        i = i + d;
        j = j + d;
        while (i < n && j < n) {
            if(text[i] < text[j])
                return true;
            if(text[i] > text[j])
                return false;
            i++;
            j++;
        }
        return i > j;
    }

    // 交换 index[i] 和 index[j]
    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    // 返回输入字符串的长度
    public int length() {
        return n;
    }

    // 返回输入字符串第i小的后缀的索引
    // text.substring(sa.index(i)) 是第i小的后缀
    public int index(int i) {
        return index[i];
    }

    // 返回返回后缀数组中第i小的后缀和第i-1小的后缀的最长公共前缀的长度
    public int lcp(int i) {
        return lcp(index[i], index[i-1]);
    }

    // text[i...n] 与text[j...n] 的最长公共前缀的长度
    private int lcp(int i, int j) {
        int length = 0;
        while (i < n && j < n) {
            if(text[i] != text[j])
                return length;
            i++;
            j++;
            length++;
        }
        return length;
    }

    // 返回第i小的后缀的字符串表示
    public String select(int i) {
        return new String(text, index[i], n - index[i]);
    }

    // 返回小于query的后缀的数量
    // rank(select(i)) = i (0 <= i <= n-1)
    public int rank(String query) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, index[mid]);
            if(cmp < 0)
                hi = mid - 1;
            else if(cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    // 返回是否 query < text[i..n]
    private int compare(String query, int i) {
        int m = query.length();
        int j = 0;
        while (i < n && j < m) {
            if(query.charAt(j) != text[i])
                return query.charAt(j) - text[i];
            i++;
            j++;
        }
        if(i < n)   // query较短,排序靠前
            return -1;
        if(j < m)   // text[i...n]较短,排序靠前
            return +1;
        return 0;
    }
}
