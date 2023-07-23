public class Reverse {
    public static void main(String [] args) {
        String string = "runoob";
        String reverse = new StringBuffer(string).reverse().toString();
        System.out.println("字符串翻转前: " + string);
        System.out.println("字符串翻转后: " + reverse);
    }
}
