import java.util.Arrays;
import java.util.StringTokenizer;

public class Split {
    public static void main(String [] args) {
        System.out.println("----------str.split方法----------");
        String str = "www-runoob-com";
        String[] temp;
        String delimeter = "-";
        System.out.println("---for循环输出方法---");
        temp = str.split(delimeter);
        for(int i = 0; i<temp.length; i++){
            System.out.println(temp[i]);
        }
        System.out.println(Arrays.toString(temp));

        System.out.println("---for each循环输方法---");
        String str1 = "www.runoob.com";
        String[] temp1;
        String delimeter1 = "\\.";
        temp1 = str1.split(delimeter1);
        for(String x : temp1){
            System.out.println(x);
        }

        System.out.println("----------StringTokenizer----------");
        String str2 = "This is String, split by StringTokenizer, created by runoob";

        System.out.println("---通过空格分隔---");
        StringTokenizer st = new StringTokenizer(str2);
        while (st.hasMoreElements()) {
            System.out.println(st.nextElement());
        }
        System.out.println("---通过逗号分隔---");
        StringTokenizer st2 = new StringTokenizer(str2, ",");
        while (st2.hasMoreElements()) {
            System.out.println(st2.nextElement());
        }
    }
}
