import java.util.*;

public class ListShuffle {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++)
            list.add(new Integer(i));
        System.out.println("打乱前: " + list);

        for(int i = 1; i < 6; i++) {
            System.out.print("第" + i + "次打乱: ");
            Collections.shuffle(list);
            System.out.println(list);
        }
    }
}