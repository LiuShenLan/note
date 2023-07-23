import java.util.Arrays;
import java.util.ArrayList;

public class Del {
    public static void main(String[] args) {
        System.out.println("-----使用数组-----");
        int[] oldArray = new int[] {3, 4, 5, 6, 7};
        int num = 2;
        int[] newArray = new int[oldArray.length - 1];
        // 判断元素是否越界
        for(int i = 0; i < newArray.length; i++) {
            if (num < 0 || num >= oldArray.length)
                throw new RuntimeException("元素越界");
            if (i < num)
                newArray[i] = oldArray[i];
            else
                newArray[i] = oldArray[i+1];
        }
        System.out.println(Arrays.toString(oldArray));
        oldArray = newArray;
        System.out.println(Arrays.toString(oldArray));

        System.out.println("-----使用ArrayList-----");
        ArrayList<String> objArray = new ArrayList<String>();
        objArray.clear();
        objArray.add(0, "第0个元素");
        objArray.add(1, "第1个元素");
        objArray.add(2, "第2个元素");
        System.out.println("数组删除元素前: " + objArray);
        objArray.remove(1);
        objArray.remove("第0个元素");
        System.out.println("数组删除元素后: " + objArray);
    }
}