import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class Traverse {
    public static void main(String[] args) {
        listTest(); // List的遍历
        setTest();  // Set的遍历
        mapTest();  // Map遍历
    }

    private static void listTest() {
        List<String> list = new ArrayList<String>();
        list.add("菜");
        list.add("鸟");
        list.add("教");
        list.add("程");
        list.add("www.runoob.com");

        // 使用iterator遍历
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            String value = it.next();
            System.out.print(value + " ");
        }
        System.out.println();

        // 使用for循环进行遍历
        for(int i = 0, size = list.size(); i < size; i++) {
            String value = list.get(i);
            System.out.print(value + " ");
        }
        System.out.println();

        // 使用for-each循环进行遍历
        for(String value : list)
            System.out.print(value + " ");
        System.out.println();
    }

    private static void setTest() {
        Set<String> set = new HashSet<String>();
        set.add("JAVA");
        set.add("C");
        set.add("C++");
        set.add("JAVA");
        set.add("JAVASCRIPT");

        // 使用iterator进行遍历
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            String value = it.next();
            System.out.print(value + " ");
        }
        System.out.println();

        // 使用for-each进行遍历
        for(String value : set)
            System.out.print(value + " ");
        System.out.println();
    }

    private static void mapTest() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("1", "PHP");
        maps.put("2", "Java");
        maps.put("3", "C");
        maps.put("4", "C++");
        maps.put("5", "HTML");

        // 使用iterator + keySet()进行遍历
        Set<String> traditional_set_key = maps.keySet();
        Iterator<String> it_key = traditional_set_key.iterator();
        while(it_key.hasNext()) {
            String key = it_key.next();
            String value = maps.get(key);
            System.out.print(key + ": " + value + "   ");
        }
        System.out.println();

        // 使用iterator + entrySet()进行遍历
        Set<Map.Entry<String, String>> traditional_set_entry = maps.entrySet();
        Iterator<Entry<String, String>> it_entry = traditional_set_entry.iterator();
        while(it_entry.hasNext()) {
            Map.Entry<String, String> entry = (Entry<String, String>) it_entry.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.print(key + ": " + value + "   ");
        }
        System.out.println();

        // 使用for-each + keySet()进行遍历
        Set<String> set_ket = maps.keySet();
        for(String s : set_ket) {
            String key = s;
            String value = maps.get(s);
            System.out.print(key + ": " + value + "   ");
        }
        System.out.println();

        // 使用for-each + entrySet()进行遍历
        Set<Entry<String, String>> set_entry = maps.entrySet();
        for(Entry<String, String> entry : set_entry) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.print(key + ": " + value + "   ");
        }
        System.out.println();
    }
}