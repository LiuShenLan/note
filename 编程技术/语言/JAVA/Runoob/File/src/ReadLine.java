import java.io.*;

public class ReadLine {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("file.txt"));
            String str;
            while ((str = in.readLine()) != null)
                System.out.println(str);
            System.out.println(str);
        } catch (IOException e) {
        }
    }
}