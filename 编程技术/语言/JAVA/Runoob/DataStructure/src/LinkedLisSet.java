import java.util.LinkedList;

public class LinkedLisSet {
    public static void main(String[] args) {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");
        System.out.println(lList);
        lList.set(2, "M");
        System.out.println(lList);
    }
}