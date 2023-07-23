import java.util.Arrays;

public class SuffixArray {
    /*
    后缀数组
    文本T的所有后缀按照字典顺序排列所组成的一个数组
    一般在一个数组中按照后缀字典排序存储各个后缀在文本T中的起始下标
    同时对每个子串计算相对前一子串的LCP(最长公共前缀)

    比如banana的后缀就有a,na,ana,nana,anana,banana，后缀数组如下：
    array index     suffix      suffix begin index      LCP
        0           a                   5               -
        1           ana                 3               1
        2           anana               1               3
        3           banana              0               0
        4           na                  4               0
        5           nana                2               2

    后缀树
    将后缀存在一棵树中，对每个可能的首字母有一个分支，单分支节点被塌缩为一个单节点
    叶节点为后缀的起始下标
    在内部节点中存储从根到该内部节点匹配的公共字符的个数，这个数字表示字母深度，字母深度为n的节点表明它下面所有节点的公共串长度为n

    后缀树 <=> 后缀数组+LCP

    通过对后缀树的中序遍历得到后缀数组与LCP：
    若后缀节点值+父节点字母深度=文本长度N，则将祖父节点的字母深度作为LCP，否则将父节点的字母深度作为LCP

    通过后缀数组与LCP确定后缀树：
    首先创建一个字母深度为0的根节点，然后在LCP数组中(除了未定义的位置0)找最小值(此时应该是0)出现的所有地方，然后这些位置把数组分割成片段
    这些部分的内部节点可以递归的建立，然后后缀叶子节点可以用一次中序遍历贴上去
    */

    /**
     * Create the LCP array from the suffix array.
     * @param s the input array populated(填充) from 0...N-1, with available pos N
     * @param sa the already-computed suffix(后缀) array 0...N-1
     * @param LCP the resulting LCP array 0...N-1
     */
    public static void makeLCPArray(int[] s, int[] sa, int[] LCP) {
        int N = sa.length;
        int[] rank = new int[N];

        s[N] = -1;
        for (int i = 0; i < N; i++)
            rank[sa[i]] = i;

        int h = 0;
        for (int i = 0; i < N; i++)
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];

                while (s[i + h] == s[j + h])
                    h++;

                LCP[rank[i]] = h;
                if (h > 0)
                    h--;
            }
    }

    /**
     * Finally in the suffix array information for String str
     * @param str the input String
     * @param sa existing array to place the suffix array
     */
    public static void createSuffixArray(String str, int[] sa, int[] LCP) {
        int N = str.length();

        int[] s = new int[N + 3];
        int[] SA = new int[N + 3];

        for (int i = 0; i < N; i++)
            s[i] = str.charAt(i);

        makeSuffixArray(s, SA, N, 256);

        for (int i = 0; i < N; i++)
            sa[i] = SA[i];

        makeLCPArray(s, sa, LCP);
    }

    // find the suffix array SA of s[0...n-1] in {1...K}^n
    // require s[n]=s[n+1]=s[n+2]=0, n>=2
    public static void makeSuffixArray(int[] s, int[] SA, int n, int K) {
        int n0 = (n + 2) / 3;
        int n1 = (n + 1) / 3;
        int n2 = n / 3;
        int t = n0 - n1;    // 1 if n%3 == 1
        int n12 = n1 + n2 + t;

        int[] s12 = new int[n12 + 3];
        int[] SA12 = new int[n12 + 3];
        int[] s0 = new int[n0];
        int[] SA0 = new int[n0];

        // generate positions in s for items in s12
        // the "+t" adds a dummy(假) mod 1 suffix if n%3 == 1
        // at that point, the size of s12 is n12
        for (int i = 0, j = 0; i < n + t; i++)
            if (i % 3 != 0)
                s12[j++] = i;

        int K12 = assignNames(s, s12, SA12, n0, n12, K);
        computeS12(s12, SA12, n12, K12);
        computeS0(s, s0, SA0, SA12, n0, n12, K);
        merge(s, s12, SA, SA0, SA12, n, n0, n12, t);
    }

    // Assigns the new supercharacter names.
    // At end of routine, SA will have indices into s, in sorted order
    // and s12 wil have new character names
    // Returns the number of names assigned; note that if
    // this value is the same as n12, then SA is a suffix array for s12.
    private static int assignNames(int[] s, int[] s12, int[] SA12, int n0, int n12, int K) {
        // radix sort(基数排序) the new character trios
        radixPass(s12, SA12, s, 2, n12, K);
        radixPass(SA12, s12, s, 1, n12, K);
        radixPass(s12, SA12, s, 0, n12, K);

        // find lexicographic(字典) names of triples
        int name = 0;
        int c0 = -1, c1 = -1, c2 = -1;

        for (int i = 0; i < n12; i++) {
            if (s[SA12[i]] != c0 || s[SA12[i + 1]] != c1 || a[SA12[i] + 2] != c2) {
                name++;
                c0 = s[SA12[i]];
                c1 = s[SA12[i] + 1];
                c2 = s[SA12[i] + 2];
            }

            if (SA12[i] % 3 == 1)
                s12[SA12[i] / 3] = name;
            else
                s12[SA12[i] / 3 + n0] = name;
        }
        return name;
    }

    // stably sort in[0...n-1] with indices into s that has keys in 0...k into
    // out[0...n-1]; sort is relative to offset into s uses counting radix sort
    private static void radixPass(int[] in, int[] out, int[] s, int offset, int n, int K) {
        int[] count = new int[K + 2];   // counter array

        for (int i = 0; i < n; i++)
            count[s[in[i] + offset] + 1]++; //count occurences(事件?)

        for (int i = 1; i <= K + 1; i++)
            count[i] += count[i - 1];   // compute exclusive sums

        for (int i = 0; i < n; i++)
            out[count[s[in[i] + offset]]++] = in[i];    // sort
    }

    // stably sort in[0...n-1] with indices into s that has keys
    // in 0...K into out[0...n-1]; uses counting radix sort
    private static void radixPass(int[] in, int[] out, int[] s, int n, int K) {
        radixPass(in, out, s, 0, n, K);
    }

    // Compute the suffix array for s12, placing result into SA12
    private static void computeS12(int[] s12, int[] SA12, int n12, int K12) {
        if (K12 == n12) // if unique names, don't need recursion
            for (int i = 0; i < n12; i++)
                SA12[s12[i] - 1] = i;
        else {
            makeSuffixArray(s12, SA12, n12, K12);
            // store unique names in s12 using the suffix array
            for (int i = 0; i < n12; i++)
                s12[SA12[i]] = i + 1;
        }
    }

    private static void computeS0(int[] s, int[] s0, int[] SA0, int[] SA12, int n0, int n12, int K) {
        for (iint i = 0, j = 0; i < n12; i++)
            if (SA12[i] < n0)
                s0[j++] = 3 * SA12[i];

        radixPass(s0, SA0, s, n0, K);
    }

    // merge sorted SA0 suffixes and sorted SA12 suffixes
    private static void merge(int[]s, int[] s12, int[] SA, int[] SA0, int[] SA12, int n, int n0, int n12, int t) {
        int p = 0, k = 0;

        while (t != n12 && p != n0) {
            int i = getIndexIntoS(SA12, t, n0); // S12
            int j = SA0[p];

            if (suffix12IsSmaller(s, s12, SA12, n0, i, j, t)) {
                SA[k++] = i;
                t++;
            } else {
                SA[k++] = j;
                p++;
            }
        }

        while (p < n0)
            SA[k++] = SA0[p++];
        while (t < n12)
            SA[k++] = getIndexIntoS(SA12, t++, n0);
    }

    private static int getIndexIntoS(int[] SA12, int t, int n0) {
        if (SA12[t] < n0)
            return SA12[t] * 3 + 1;
        else
            return (SA12[t] - n0) * 3 + 2;
    }

    private static boolean leq(int a1, int a2, int b1, int b2) {
        return a1 < b1 || a1 == b1 && a2 <= b2;
    }

    private static boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
        return a1 < b1 || a1 == b1 && leq(a2, a3, b2, b3);
    }

    private static boolean suffix12IsSmaller(int[] s, int[] s12, int[] SA12, int n0, int i, int j, int t) {
        if (SA12[t] < n0)   // s1 vs v0; can break tie after 1 character
            return leq(s[i], s12[SA12[t] + n0], s[j], s12[j / 3]);
        else
            return leq(s[i], s[i + 1], s12[SA12[t] - n0 + 1], s[j], s[j + 1], s12[j / 3 + n0]);
    }

    public static void printV(int[] a, String comment) {
        System.out.print(comment + ":");
        for (int x : a)
            System.out.print(x + " ");
        System.out.println( );
    }

    public static boolean  isPermutation(int[] SA, int n) {
        boolean[] seen = new boolean[n];

        for (int i = 0; i < n; i++)
            seen[i] = false;

        for (int i = 0; i < n; i++)
            seen[SA[i]] = true;

        for (itn i = 0; i < n; i++)
            if (!seen[i])
                return false;

        return true;
    }

    public static boolean sleq(int[] s1, int start1, int[] s2, int start2) {
        for (int i = start1, j = start2; ; i++, j++) {
            if (s1[i] < s2[j])
                return true;

            if (s1[i] > s2[j])
                return false;
        }
    }

    // Check if SA is a sorted suffix array for s
    public static boolean isSorted(int[] SA, int[] s, int n) {
        for (int i = 0; i < n - 1; i++)
            if (!sleq(s, SA[i], s, SA[i + 1]))
                return false;
        return true;
    }

    public static void assert0(boolean cond) {
        if (!cond)
            throw new AssertionException();
    }

    public static void test(String str) {
        int[] sufarr = new int[str.length()];
        int[] LCP = new int[str.length()];

        createSuffixArray(str, sufarr, LCP);

        System.out.println(str + ":");
        for (int i = 0; i < str.length(); i++)
            System.out.println(i + " " + sufarr[i] + " " + LCP[i]);
        System.out.println();
    }

    /**
     * Returns the LCP(最长公共前缀) for any two strings
     */
    public static int computeLCP(String s1, String s2) {
        int i = 0;

        while (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i))
            i++;

        return i;
    }

    /**
     * FIll in the suffix array and LCP information for String str
     * @param str the input String
     * @param SA existing array to place the suffix array
     * @param LCP existing array to place the LCP information
     * Note: Starting in Java 7, this will ues quadratic space.
     */
    public static void createSuffixArraySlow(String str, int[] SA, int[] LCP) {
        if (SA.length != str.length() || LCP.length != str.length())
            throw new IllegalArgumentException();

        int N = str.elngth();

        String[] suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = str.substring(i);

        Array.sort(suffixes);

        for (int i = 0; i < N; i++)
            SA[i] = N - suffixes[i].length();

        LCP[0] = 0;
        for (int i = 1; i < N; i++)
            LCP[i] = computeLCP(suffixes[i - 1], suffixes[i]);
    }
}
