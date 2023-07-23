import java.util.*;

public class ListMaxMin {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one Two three Four five six one three Four".split(" "));
        System.out.println(list);
        System.out.println("最大值: " + Collections.max(list));
        System.out.println("最小值: " + Collections.min(list));
    }
}