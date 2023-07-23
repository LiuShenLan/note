class thread implements Runnable {
    public void run() {
        try { 
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }

        System.out.println("State of thread1 while it called join() method on thread2 -" + GetState2.thread1.getState()); 
        try { 
            Thread.sleep(200); 
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }
}
  
public class GetState2 implements Runnable {
    public static Thread thread1; 
    public static GetState2 obj; 
      
    public static void main(String[] args) {
        obj = new GetState2(); 
        thread1 = new Thread(obj); 
          
        // 创建 thread1，现在是初始状态
        System.out.println("State of thread1 after creating it - " + thread1.getState()); 
        thread1.start(); 
          
        // thread1 - 就绪状态
        System.out.println("State of thread1 after calling .start() method on it - " + thread1.getState()); 
    }
      
    public void run() {
        thread myThread = new thread(); 
        Thread thread2 = new Thread(myThread); 
          
        // 创建 thread1，现在是初始状态
        System.out.println("State of thread2 after creating it - "+ thread2.getState()); 
        thread2.start(); 
          
        // thread2 - 就绪状态
        System.out.println("State of thread2 after calling .start() method on it - " + thread2.getState()); 
          
        // moving thread1 to timed waiting state 
        try {
            //moving - 超时等待
            Thread.sleep(200); 
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
        System.out.println("State of thread2 after calling .sleep() method on it - " + thread2.getState() );        

        try {
            // 等待 thread2 终止
            thread2.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
        System.out.println("State of thread2 when it has finished it's execution - " + thread2.getState()); 
    }
}