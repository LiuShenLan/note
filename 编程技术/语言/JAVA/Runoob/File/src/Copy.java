import java.io.*;

public class Copy {
    public static void main(String[] args) throws Exception {
        // 创建文件
        BufferedWriter out1 = new BufferedWriter(new FileWriter("srcfile"));
        out1.write("String to be copied\n");
        out1.close();
        // 读取文件
        InputStream in = new FileInputStream(new File("srcfile"));
        // 输出文件
        OutputStream out = new FileOutputStream(new File("destnfile"));
        byte[] buf = new byte[1024];
        int len;
        // 复制文件
        while((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        in.close();
        out.close();
        // 打印文件
        BufferedReader in1 = new BufferedReader(new FileReader("destnfile"));
        String str;
        while ((str = in1.readLine()) != null)
            System.out.println(str);
        in1.close();
    }
}