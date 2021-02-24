package com.mrl.mianshi;

import com.sun.org.glassfish.gmbal.Description;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SaleTicketTest
 * @Description 卖票  低头干活 抬头看路
 * 题目：三个售票员 卖出 30张票
 * @Author lwq
 * @Date 2021/2/4 11:37
 * @Version 1.0
 */
public class SaleTicketTest {

    static ExecutorService executorService1;
    static ExecutorService executorService2;

    static {
        //int corePoolSize,
        //                              int maximumPoolSize,
        //                              long keepAliveTime,
        //                              TimeUnit unit,
        //                              BlockingQueue<Runnable> workQueue,
        //                              ThreadFactory threadFactory,
        //                              RejectedExecutionHandler handler
        executorService1 = Executors.newFixedThreadPool(3);
        executorService2 = Executors.newFixedThreadPool(3);

    }


    public static void main(String[] args) {
        Ticket ticket = new Ticket(100);
        executorService1.execute(() -> {
            while (true) {
               // ticket.saleTicket(Thread.currentThread().getName(), 1);

                ticket.saleTicket();
            }
        });

//        executorService2.execute(() -> {
//            while (true) {
//                ticket.prouduceTicket(1, 200);
//            }
//        });






       /* //30张票 资源类
        Ticket ticket = new Ticket(1000);


         Thread thread1 = new Thread(()->{

             while (true){
                 ticket.saleTicket("售票员A",1);
             }

          },"售票员A");
        thread1.start();
        Thread thread2 = new Thread(()->{

            while (true){
                ticket.saleTicket("售票员B",1);
            }

        },"售票员B");
        thread2.start();
        Thread thread3 = new Thread(()->{

            while (true){
                ticket.saleTicket("售票员C",1);
            }

        },"售票员B");
        thread3.start();*/


    }
}

/**
 * 资源类（对自己操作的方法 对外暴露的调用方法）
 */
class Ticket {


    static Lock lock = new ReentrantLock();

    static Condition conditionA = lock.newCondition();
//    static Condition conditionB = lock.newCondition();

    /**
     * 票的数量
     */
    private Integer num;

    public Ticket(Integer num) {
        this.num = num;
    }


    public synchronized void saleTicket(){
        if(num==0){
           // System.out.println("票已卖完...");
        }else{
            System.out.println(Thread.currentThread().getName()+"卖出票,票号"+num--);
        }
    }


    /**
     * 卖票方法
     */
    public void saleTicket(String name, Integer saleNum) {


        // 加锁 lock 或者 synchronized块
        lock.lock();
        try {

            while (num == 0) {
//            System.out.println(Thread.currentThread().getName());
                //System.out.println(name + "卖出票" + saleNum);

                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            num -= saleNum;
            System.out.println("卖票：剩余票:" + num);

            conditionA.signal();

        } finally {
            lock.unlock();
        }


    }


    /**
     * 生产票
     */
    public void prouduceTicket(Integer proNum, Integer maxNum) {
        lock.lock();
        try {

            while (num >= maxNum) {

                System.out.println("生产票：剩余票：" + num);
                //达到最大值不继续生产了
                //阻塞在这里
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            num += proNum;
            System.out.println("生产票：剩余票：" + num);

            //通知继续卖票
            conditionA.signal();


        } finally {
            lock.unlock();
        }
    }

    /**
     * @param x   测试1
     * @param y   测试2
     * @param arr
     * @param d
     * @return java.lang.String 测试43
     * @Author lwq
     * @Date 2021/2/4 14:13
     * @Description TODO
     **/
    public String etst(String x, int y, int[] arr, Object d) {
        return null;
    }
}
