import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

class SetMinMax {
    public static void main(String[] args) {
        String[] coins = {"Penny", "nickel", "dime", "Quarter", "dollar"};
        Set<String> set = new TreeSet<String>();
        for(int i = 0; i < coins.length; i++)
            set.add(coins[i]);
        System.out.println(Collections.min(set));
        System.out.println(Collections.min(set, String.CASE_INSENSITIVE_ORDER));    // 首先按照字符串长短排序，相同长度一次按照ASCII码排序
        for(int i = 0; i <= 10; i++)
            System.out.print("-");
        System.out.println();
        System.out.println(Collections.max(set));
        System.out.println(Collections.max(set, String.CASE_INSENSITIVE_ORDER));
    }
}