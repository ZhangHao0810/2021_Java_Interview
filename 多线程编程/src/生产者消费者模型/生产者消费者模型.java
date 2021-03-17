package 生产者消费者模型;

import 实现多线程.RunnableDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Super-Zhang
 * @date 2021-03-17 10:18
 */
public class 生产者消费者模型 {

    private int procude;
    private int max_produce=77;


    //生产者
    public synchronized  void produce(){
        if (this.procude >= max_produce) {
            try {
                System.out.println("仓库已满，无法生产。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.procude++;
        System.out.println("生产者生产第 "+this.procude +" 个商品");
        notifyAll();//通知缺货等待的消费者可以消费了。
    }

    //消费者
    public  synchronized void consume(){

        if (this.procude<=0){
            try {
//                System.out.println("缺货，稍后再取");
                wait();
                System.out.println("缺货，稍后再取");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("消费者取走了第 "+this.procude+ "个商品");
        this.procude--;
        notifyAll();//通知仓库满了的生产者可以生产了。
    }

    public static void main(String[] args) {

        生产者消费者模型 object1 = new 生产者消费者模型();


            Runnable produce = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        object1.produce();
                    }
                }
            };

            Runnable consume = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        object1.consume();
                    }
                }
            };

            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(produce);
            executor.execute(consume);


    }


}
