package com.mrl.mianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ProductConsumerBlockQueueDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/15 13:45
 * @Version 1.0
 */
public class ProductConsumerBlockQueueDemo {

    public static void main(String[] args) {


        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(1));

        Thread threadName = new Thread(() -> {
            try {
                myResource.myPro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Prod");
        threadName.start();



        Thread threadName2 = new Thread(() -> {
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer");
        threadName2.start();

        Thread threadName3 = new Thread(() -> {
            try {
                myResource.myPro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Prod");
        threadName3.start();



        Thread threadName4 = new Thread(() -> {
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer");
        threadName4.start();



        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
         System.out.println("5秒时间到"+Thread.currentThread().getName()+"\t"+"结束....");

    }

}

class MyResource{

    /**
     * 标记位
     */
    private volatile Boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    private Integer num = 0;


    private BlockingQueue<String> blockingQueue = null;

    /**
     * 传入实现类
     * @param blockingQueue
     */
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产
     */
    public void myPro() throws InterruptedException {
        String value = "";
        while (FLAG){

            value =  atomicInteger.incrementAndGet()+"";
//            value =  (++num) +"";

            //生产 2秒内尝试王队列放入元素 放入不了则不继续阻塞
            boolean offer = blockingQueue.offer(value, 2L, TimeUnit.SECONDS);

            if(offer){
                 System.out.println(Thread.currentThread().getName()+"\t 插入队列"+value+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+value+"失败");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

         System.out.println(Thread.currentThread().getName()+"\t"+"生产结束");

    }

    public void myConsumer() throws InterruptedException {
        String poll = "";
        while (FLAG){
            poll  = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if(poll==null || "".equals(poll)){
                FLAG = false;
                 System.out.println(Thread.currentThread().getName()+"2秒未取到数据..退出");
                 return;
            }

            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+poll+"成功");



        }
    }


    public void stop(){
        this.FLAG = false;
    }
}
