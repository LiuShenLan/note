import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    // Rabin-Karp指纹字符串查找算法
    // 该字符串查找算法的基数是散列,在构造函数中计算模式字符串的散列值并在文本中查找该散列值的匹配

    private String pat; // 模式字符串(仅forLas Vegas拉斯维加斯算法需要)
    private long patHash;   // 模式字符串的散列值
    private int m;  // 模式字符串的长度
    private long q; // 一个很大的素数
    private int R;  // 基数,字母表大小
    private long RM;    // R^(M-1) % Q

    // 预处理模式字符串
    public RabinKarp(String pat) {
        this.pat = pat; // 保存模式字符串(仅拉斯维加斯算法需要)
        R = 256;    // 基数,字母表大小
        m = pat.length();   // 模式字符串的长度
        q = longRandomPrime();  // 31位的随机素数

        // 预计算 R^(m-1) % q 用于删除前导数字
        RM = 1;
        for(int i = 1; i <= m-1; i++)   // 计算 R^(m-1) % q
            RM = (R * RM) % q;
        patHash = hash(pat, m); // 计算key[0..m-1]的散列值
    }

    // Horner方法,用于除留余数法计算key[0..m-1]的散列值
    private long hash(String key, int m) {
        long h = 0;
        for(int j = 0; j < m; j++)
            h = (R * h + key.charAt(j)) % q;
        return h;
    }

    // 拉斯维加斯算法:模式pat[]与文本txt[i..i-m+1]是否匹配
    private boolean check(String txt, int i) {
        for(int j = 0; j < m; j++)
            if(pat.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    // 返回模式字符串在文本字符串中第一次出现的索引
    public int search(String txt) {
        // 在文本中查找相等的散列值
        int n = txt.length();

        if(n < m)   // 文本比模式短,不匹配
            return n;

        long txtHash = hash(txt, m);    // 文本前m个字符的散列值

        // 从偏差0处开始查看是否匹配
        if((patHash == txtHash) && check(txt, 0))   // 一开始就匹配成功
            return 0;

        // 查看散列值是否匹配,如果散列值匹配则check()
        for(int i = m; i < n; i++) {
            // 减去第一个数字,加上最后一个数字,在此检查匹配
            txtHash = (txtHash + q - RM * txt.charAt(i-m) % q) % q; // 减去第一个数字,+q是使结果为正以便进行求余计算
            txtHash = (txtHash * R + txt.charAt(i)) % q;    // 加上最后一个数字

            // 查看是否匹配
            int offset = i - m + 1;
            if((patHash == txtHash) && check(txt, offset))  // 找到匹配
                return offset;
        }
        return n;   // 未找到匹配
    }

    // 返回随机的31位素数
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
