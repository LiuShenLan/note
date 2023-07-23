import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableEnumeration {
    public static void main(String[] args) {
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("1", "One");
        ht.put("2", "Two");
        ht.put("3", "Three");
        Enumeration e = ht.keys();
        while(e.hasMoreElements())
            System.out.println(e.nextElement());
        e = ht.elements();
        while(e.hasMoreElements())
            System.out.println(e.nextElement());
    }
}