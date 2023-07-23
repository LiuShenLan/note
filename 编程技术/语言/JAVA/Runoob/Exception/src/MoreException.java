class Demo{
    int div(int a, int b) throws ArithmeticException, ArrayIndexOutOfBoundsException {  // 在功能上通过throws的关键字声明该功能可能出现问题
        int[] arr = new int[a];

        System.out.println(arr[4]); // 制造第一处异常

        return a/b;     //制造第二处异常
    }
}

class MoreException {
    public static void main(String[] args) {
        Demo d = new Demo();
        try {
            // int x = d.div(4, 0);
            // int x = d.div(5, 0);
            int x = d.div(5, 1);
            System.out.println("x=" + x);
        }
        catch (ArithmeticException e) {
            System.out.println(e.toString());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());            
        }
        catch (Exception e) {   // 父类写在此处是为了捕捉其他没有预料到的异常，只能写在子类异常的后阿敏，不过一般情况下不写
            System.out.println(e.toString());
        }
        System.out.println("Finish");
    }
}