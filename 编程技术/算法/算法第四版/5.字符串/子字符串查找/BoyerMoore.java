public class BoyerMoore {
    // Boyer-Moore字符串匹配算法(启发式的处理不匹配字符)
    // 从右向左扫描模式字符串,并在匹配失败时通过跳跃将文本中的字符和它在模式字符串中出现的最右位置对齐

    private final int R;    // 基数
    private int[] right;    // 不匹配字符跳跃数组

    private char[] pattern; // 将pattern存储为字符数组
    private String pat; // 将pattern存储为字符串

    // 处理pattern字符串
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // 字符c在pattern中出现的最右侧的位置
        right = new int[R];
        for(int c = 0; c < R; c++)
            right[c] = -1;  // 不包含在模式字符串中的字符的值为1
        for(int j = 0; j < pat.length(); j++)  // 包含在模式字符串中的字符的值
            right[pat.charAt(j)] = j;   // 值为它在其中出现的最右的位置
    }

    // 处理pattern字符串
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];

        for(int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // 字符c在pattern中出现的最右侧的位置
        right = new int[R];
        for(int c = 0; c < R; c++)
            right[c] = -1;
        for(int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    // 返回模式字符串第一次在文本字符串中出现的位置
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for(int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for(int j = m-1; j >= 0; j--) { // 将模式字符串从右向左与文本字符串进行匹配
                if(pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if(skip == 0)
                return i;   // 找到匹配
        }
        return n;   // 未找到匹配
    }


    // 返回模式字符串第一次在文本字符串中出现的位置
    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for(int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for(int j = m-1; j >= 0; j--) {
                if(pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if(skip == 0)
                return i;   // 找到匹配
        }
        return n;   // 未找到匹配
    }
}
