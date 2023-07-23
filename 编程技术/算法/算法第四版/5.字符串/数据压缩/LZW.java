public class LZW {
    // LZW压缩算法
    // 为输入中的变长模式生成一张定长的编码编译表,和霍夫曼编码不同,输出中不需要附上这张编译表

    /*
    压缩:
    找出未处理的输入在符号表中最长的前缀字符串s
    输出s的8位值(编码)
    继续扫描s之后的一个字符c
    在符号表中将s+c(连接s和c)的值设为下一个编码值

    展开:
    输出当前字符串val
    从输入中读取一个编码x
    在符号表中将s设为和x相关联的值
    在符号表中将下一个未分配的编码值设为val+c,其中c为s的首字母
    将当前字符串val设为s
    */

    private static final int R = 256;   // 输入字符数
    private static final int L = 4096;  // 编码总数 = 2^W   本实例扩展的实现为8位输入12位输出
    private static final int W = 12;    // 编码宽度

    // 从标准输入读取8位字节序列,使用12位编码的LZW算法进行压缩,并将结果写入标准输出
    // 使用三向单词查找树表示编译表
    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();  // 三向单词查找树

        for(int i = 0; i < R; i++) // 将标准ASCII字节插入三向单词查找树
            st.put("" + (char) i, i);

        int code = R+1;  // R(=256)为EOF(文件末尾)编码

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);   // 找到匹配输入的最长前缀
            BinaryStdOut.write(st.get(s), W);   // 打印s的编码

            int t = s.length();
            if(t < input.length() && code < L) // 未到输入末尾并且编码表还有空闲
                st.put(input.substring(0, t + 1), code++);  // 将 s+s的下一个字符加入编码表

            input = input.substring(t); // 从输入中忽略s
        }
        BinaryStdOut.write(R, W);   // 写入文件结束标记
        BinaryStdOut.close();
    }

    // 从标准输入中读取使用12位编码的LZW压缩的字节序列并解压,将结果写入标准输出
    // 使用字符串数组表示逆向编译表
    public static void expand() {
        String[] st = new String[L];    // 逆向编译表

        int i;  // 下一个待补全的编码值

        // 使用单个字符初始化编译表
        for(i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";   // (未使用)文件结束标记EOF的前瞻字符

        int codeword = BinaryStdIn.readInt(W);
        if(codeword == R)  // 扩展信息是空字符串
            return ;
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);    // 输出当前子字符串

            codeword = BinaryStdIn.readInt(W);  // 读取下一个编码

            if(codeword == R)  // 到达文件末尾
                break;

            String s = st[codeword];    // 获取下一个编码所对应的字符串
            if(i == codeword)  // 需要读取的编码刚好是需要写入的编码(特殊情况)
                s = val + val.charAt(0);    // 根据上一个字符串的首字母得到编码的字符串

            if(i < L)  // 编译表还有空闲位置
                st[i++] = val + s.charAt(0);    // 为编译表添加新的条目
            val = s;    // 更新当前编码
        }
        BinaryStdOut.close();
    }
}
