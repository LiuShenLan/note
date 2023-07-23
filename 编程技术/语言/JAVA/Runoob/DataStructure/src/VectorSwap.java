import java.util.Collections;
import java.util.Vector;

public class VectorSwap {
    public static void main(String[] args) {
        Vector<String> v = new Vector<String>();
        v.add("1");
        v.add("2");
        v.add("3");
        v.add("4");
        v.add("5");
        System.out.println("交换前: " + v);
        Collections.swap(v, 0, 4);
        System.out.println("交换后: " + v);
    }
}