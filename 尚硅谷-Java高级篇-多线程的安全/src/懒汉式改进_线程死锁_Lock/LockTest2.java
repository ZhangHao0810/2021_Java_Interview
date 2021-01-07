package 懒汉式改进_线程死锁_Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Super-Zhang
 * @date 2021-01-07 19:36
 *  继承Thread方式 使用Lock锁
 */

class Window2 extends Thread{

    //票也是共享资源，一定要保证 共享资源要能共享！ 设置为静态的。
    private static int ticket = 100;
    //1.实例化ReentrantLock  因为是继承Thread
    //这个锁必须得是静态的，以保证线程之间共享锁资源。
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{

                //2.调用锁定方法lock()
                lock.lock();

                if(ticket > 0){

//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //3.调用解锁方法：unlock()
                lock.unlock();
            }

        }
    }
}

public class LockTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}


