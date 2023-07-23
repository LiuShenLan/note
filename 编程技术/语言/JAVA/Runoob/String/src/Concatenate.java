public class Concatenate {
    public static void main(String [] args){
        System.out.println("-----循环内新建字符串进行链接-----");
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            String result = "This is" + " testing the" +
                    " difference" + " between" + " String" + " and" + " StringBuffer";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("字符串连接" + " - 使用 + 操作符: " + (endTime - startTime) + "ms");

        long startTime1 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            StringBuffer result = new StringBuffer();
            result.append("This is");
            result.append(" testing the");
            result.append(" difference");
            result.append(" between");
            result.append(" String");
            result.append(" and");
            result.append(" StringBuffer");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("字符串连接" + " - 使用 StringBuffer: " + (endTime1 - startTime1) + "ms");

        System.out.println("-----循环内字符串不新建进行链接-----");
        long startTime2 = System.currentTimeMillis();
        String result2 = null;
        for(int i = 0; i < 50000; i++){
            result2 += "This is" + " testing the" +
                    " difference" + " between" + " String" + " and" + " StringBuffer";
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("字符串连接" + " - 使用 + 操作符: " + (endTime2 - startTime2) + "ms");

        StringBuffer result3 = new StringBuffer();
        long startTime3 = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            result3.append("This is");
            result3.append(" testing the");
            result3.append(" difference");
            result3.append(" between");
            result3.append(" String");
            result3.append(" and");
            result3.append(" StringBuffer");
        }
        long endTime3 = System.currentTimeMillis();
        System.out.println("字符串连接" + " - 使用 StringBuffer: " + (endTime3 - startTime3) + "ms");
    }
}
