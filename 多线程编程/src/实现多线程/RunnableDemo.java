package 实现多线程;

/**
 * @author Super-Zhang
 * @date 2021-03-17 9:41
 */
class RunnableDemo1 implements Runnable {

    private Thread t;
    private String threadName;

    RunnableDemo1(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );

    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class RunnableDemo {
    public static void main(String args[]) {
        RunnableDemo1 R1 = new RunnableDemo1( "Thread-1");
        R1.start();
        RunnableDemo1 R2 = new RunnableDemo1( "Thread-2");
        R2.start();
    }
}
