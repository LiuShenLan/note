public class KMP {
    // Knuth-Morris-Pratt字符串查找算法
    private final int R;    // 基数
    private final int m;    // 模式的长度
    private int[][] dfa;    // 确定有限状态自动机

    public KMP(String pat) {
        this.R = 256;
        this.m = pat.length();

        // 由模式字符串构造DFA
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for(int x = 0, j = 1; j < m; j++) {
            for(int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];  // 复制匹配失败情况下的值
            dfa[pat.charAt(j)][j] = j+1;    // 设置匹配成功情况下的值
            x = dfa[pat.charAt(j)][x];  // 更新重启状态
        }
    }

    public KMP(char[] pattern, int R) {
        this.R = R;
        this.m = pattern.length;

        // 由模式字符串构造DFA
        dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        for(int x = 0, j = 1; j < m; j++) {
            for(int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];  // 复制匹配失败情况下的值
            dfa[pattern[j]][j] = j+1;   // 设置匹配成功情况下的值
            x = dfa[pattern[j]][x]; // 更新重启状态
        }
    }

    // 返回模式字符串在文本字符串中首次出现的索引
    public int search(String txt) {

        int n = txt.length();
        int i, j;
        for(i = 0, j = 0; i < n && j < m; i++)
            j = dfa[txt.charAt(i)][j];

        if(j == m)
            return i - m;   // 找到匹配(到达模式字符串的结尾)
        return n;   // 未找到匹配(到达文本字符串的结尾)
    }

    // 返回模式字符串在文本字符串中首次出现的索引
    public int search(char[] text) {

        int n = text.length;
        int i, j;
        for(i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text[i]][j];
        }
        if(j == m)
            return i - m;   // 找到匹配(到达模式字符串的结尾)
        return n;   // 未找到匹配(到达文本字符串的结尾)
    }
}
