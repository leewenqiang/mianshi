package com.mrl.mianshi.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SyncBlockQueueDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/8 17:27
 * @Version 1.0
 */
public class SyncBlockQueueDemo {

    public static void main(String[] args) {

        //同步阻塞队列 不存储元素 元素必须消费 才能放进去
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        Thread threadName = new Thread(() -> {
            try {
                 System.out.println(Thread.currentThread().getName()+"\t 放入元素1");
                blockingQueue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(Thread.currentThread().getName()+"\t 放入元素2");
                blockingQueue.put("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(Thread.currentThread().getName()+"\t 放入元素3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A");
        threadName.start();


        Thread threadName2 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"\t 读取数据"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"\t 读取数据"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName()+"\t 读取数据"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "B");
        threadName2.start();

    }

}
