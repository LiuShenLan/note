public class Optimization {
    public static void main(String [] args){
        String [] variables = new String[50000];
        for(int i = 0; i < 50000; i++){
            variables[i] = "s" + i;
        }

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            variables[i] = "hello";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("直接使用字符串: " + (endTime - startTime) + "ms");

        long startTime1 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            variables[i] = new String("hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用new关键字: " + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            variables[i] = new String("hello");
            variables[i] = variables[i].intern();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("使用字符串对象的intern方法: " + (endTime2 - startTime2) + "ms");
    }
}
