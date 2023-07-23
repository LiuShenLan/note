public class ComparePerformance {
    public static void main(String [] args) {
        long startTime1 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            String s1 = "hello";
            String s2 = "hello";
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("通过String关键字创建字符串: " + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            String s3 = new String("hello");
            String s4 = new String("hello");
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("通过String对象创建字符串: " + (endTime2 - startTime2) + "ms");
    }
}
