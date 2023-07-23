public class LSD {
    // 低位优先的字符串排序

    private static final int BITS_PER_BYTE = 8;

    // 升序排列宽度为w的字符串
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;   // 基数,扩展ASCII字符集
        String[] aux = new String[n];

        for(int d = w-1; d >= 0; d--) {
            // 根据第d个字符用键索引计数法排序

            // 计算出现频率
            int[] count = new int[R+1];
            for(int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // 将频率转换为索引
            for(int r = 0; r < R; r++)
                count[r+1] += count[r];

            // 将元素分类
            for(int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // 回写
            for(int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    // 升序排列32位整数数组
    public static void sort(int[] a) {
        final int BITS = 32;    // 每个整数均为32位
        final int R = 1 << BITS_PER_BYTE;   // R = 256 每个bytes均在0到255之间
        final int MASK = R - 1; // 0xFF
        final int w = BITS / BITS_PER_BYTE; // 每个整数为4bytes

        int n = a.length;
        int[] aux = new int[n];

        for(int d = 0; d < w; d++) {
            // 计算出现频率
            int[] count = new int[R+1];
            for(int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // 将频率转换为索引
            for(int r = 0; r < R; r++)
                count[r+1] += count[r];

            // 对于最高有效字节,0x80-0xFF位于0x00-0x7F之前
            if(d == w-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for(int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for(int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // 将元素分类
            for(int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = a[i];
            }

            // 回写
            for(int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }
}
