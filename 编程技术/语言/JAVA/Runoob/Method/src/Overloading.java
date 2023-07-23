class House {
    int height;

    House() {
        System.out.println("无参数构造函数");
        height = 4;
    }

    House(int i) {
        System.out.println("int参数构造函数, 房子高度为: " + i + "米");
        height = i;
    }

    void info() {
        System.out.println("房子高度为: " + height + "米");
    }

    void info(String s) {
        System.out.println(s + ": 房子高度为: " + height + "米");
    }
}

public class Overloading {
    public static void main(String[] args) {
        House t = new House(3);
        t.info();
        t.info("参数为String的重载方法");
        new House();    //重载构造方法
    }
}