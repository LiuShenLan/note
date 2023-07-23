import java.util.*;

public class ListReplaceAll {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("onr Two three Four five six one three Four".split(" "));
        System.out.println("List: " + list);
        Collections.replaceAll(list, "one", "hundrea");
        System.out.println("replaceAll: " + list);
    }
}