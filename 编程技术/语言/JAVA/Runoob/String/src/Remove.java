public class Remove {
    public static void main(String [] args) {
        String str = "this is Java";
        System.out.println(removeCharat(str,3));
    }
    public static String removeCharat(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
