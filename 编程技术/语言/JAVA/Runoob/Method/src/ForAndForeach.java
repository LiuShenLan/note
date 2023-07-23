import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForAndForeach {
    public static void main(String[] args) {
        System.out.println("--------一维数组--------");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("---for循环---");
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
        System.out.println("---foreach循环---");
        for (int element : arr)
            System.out.println(element);
        
        System.out.println("--------二维数组--------");
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int[] row : arr2)
            for (int element : row)
                System.out.println(element);
        
        System.out.println("--------遍历List--------");
        List<String> list = new ArrayList<String>();
        list.add("Google");
        list.add("Runoob");
        list.add("Taobao");
        System.out.println("---for循环---");
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
        System.out.println("---使用迭代器---");
        for (Iterator<String> iter = list.iterator(); iter.hasNext();)
            System.out.println(iter.next());
        System.out.println("---foreach循环---");
        for (String str : list)
            System.out.println(str);
    }
}