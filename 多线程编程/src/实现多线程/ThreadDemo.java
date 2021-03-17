package 实现多线程;

/**
 * @author Super-Zhang
 * @date 2021-03-17 9:27
 *  集成Thread 方式创建类的实例
 *
 *   本质上是实现了Runnable接口的一个实例。
 */
class ThreadDemo1  extends Thread{
    private Thread t;
    private String threadName;

    ThreadDemo1(String name) {
        threadName = name;
        System.out.println("creating "+threadName);
    }

    public void run() {
        System.out.println("running " + threadName);
        try {
            for (int i = 0; i <10 ; i++) {
                System.out.println("Thread: " + threadName + "==>" + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Starting" + threadName+"interrupted.");
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start(){
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

}

public class ThreadDemo {

    public static void main(String[] args) {
        ThreadDemo1 t1 = new ThreadDemo1("t-1");
        t1.start();

        ThreadDemo1 t2 = new ThreadDemo1("t-2");
        t2.start();

    }
}