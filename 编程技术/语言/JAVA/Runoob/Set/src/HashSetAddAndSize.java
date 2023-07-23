import java.util.*;

public class HashSetAddAndSize {
    public static void main(String[] args) {
        System.out.println("集合实例!");
        int size;
        HashSet<String> collections = new HashSet<String>();
        String str1 = "Yellow", str2 = "White", str3 = "Green", str4 = "Blue";
        Iterator iterator;

        collections.add(str1);
        collections.add(str2);
        collections.add(str3);
        collections.add(str4);

        System.out.print("集合数据: ");
        iterator = collections.iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        size = collections.size();
        if(collections.isEmpty())
            System.out.println("集合是空的");
        else
            System.out.println("集合长度: " + size);
    }
}