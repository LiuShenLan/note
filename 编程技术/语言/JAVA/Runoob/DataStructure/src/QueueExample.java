import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");

        for(String q : queue)
            System.out.print(q + " ");

        System.out.println("\n===");
        System.out.println("queue.poll = " + queue.poll() + " => 返回并删除第一个元素");
        for(String q : queue)
            System.out.print(q + " ");
        System.out.println("\n===");
        System.out.println("queue.element = " + queue.element() + " => 返回第一个元素");
        for(String q : queue)
            System.out.print(q + " ");
        System.out.println("\n===");
        System.out.println("queue.peek = " + queue.peek() + " => 返回第一个元素");
        for(String q : queue)
            System.out.print(q + " ");
        System.out.println("\n===");
    }
}