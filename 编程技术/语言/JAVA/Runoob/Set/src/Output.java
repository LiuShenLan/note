import java.util.*;

public class Output {
    public static void main(String[] args) {
        TreeMap<Integer, String> tMap = new TreeMap<Integer, String>();
        tMap.put(1, "Sunday");
        tMap.put(2, "Monday");
        tMap.put(3, "Tuesday");
        tMap.put(4, "Wednesday");
        tMap.put(5, "Thursday");
        tMap.put(6, "Friday");
        tMap.put(7, "Saturday");

        System.out.println("TreeMap键: " + tMap.keySet());
        System.out.println("TreeMap值: " + tMap.values());
        System.out.println("键为5的值为: " + tMap.get(5));
        System.out.println("第一个键: " + tMap.firstKey() + " Value: " + tMap.get(tMap.firstKey()));
        System.out.println("最后一个键: " + tMap.lastKey() + " Value: " + tMap.get(tMap.lastKey()));
        System.out.println("移除第一个数据: " + tMap.remove(tMap.firstKey()));
        System.out.println("现在TreeMap键为: " + tMap.keySet());
        System.out.println("现在TreeMap包含: " + tMap.values());
        System.out.println("移除最后一个数据: " + tMap.remove(tMap.lastKey()));
        System.out.println("现在TreeMap键为: " + tMap.keySet());
        System.out.println("现在TreeMap包含: " + tMap.values());
    }
}