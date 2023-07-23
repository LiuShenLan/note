import java.util.*;

public class HashSetRemove {
    public static void main(String[] args) {
        int size;
        HashSet<String> collection = new HashSet<String>();
        String str1 = "Yellow", str2 = "White", str3 = "Green", str4 = "Blue";
        Iterator iterator;
        collection.add(str1);
        collection.add(str2);
        collection.add(str3);
        collection.add(str4);
        System.out.print("集合数据: ");
        iterator = collection.iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();
        collection.remove(str2);
        System.out.println("删除数据为: " + str2);
        System.out.print("现在集合的数据为: ");
        iterator = collection.iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();
        size = collection.size();
        System.out.println("集合大小: " + size);
    }
}