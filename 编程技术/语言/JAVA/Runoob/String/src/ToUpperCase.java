public class ToUpperCase {
    public static void main(String [] args) {
        String str = "string runoob";
        String strUpper = str.toUpperCase();
        String strLower = strUpper.toLowerCase();
        System.out.println("原始字符串: " + str);
        System.out.println("转换为大写: " + strUpper);
        System.out.println("转换为小写: " + strLower);
    }
}
