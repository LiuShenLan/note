import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMillis {
    public static void main(String[] args) {
        Long timeStamp = System.currentTimeMillis();    //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); //时间戳转换成时间
        String sd1 = sdf.format(timeStamp);
        String sd2 = sdf.format(Long.parseLong(String.valueOf(timeStamp)));
        String sd3 = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.out.println(sd1);
        System.out.println(sd2);
        System.out.println(sd3);
    }
}