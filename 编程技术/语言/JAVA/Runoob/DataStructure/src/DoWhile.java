public class DoWhile {
    public static void main(String[] args) {
        int limit = 100, sum = 0, i = 1;
        do {
            sum += i;
            i++;
        }while(i <= limit);
        System.out.println("1+2+3+...+100=" + sum);
    }
}