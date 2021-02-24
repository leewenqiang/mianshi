package com.mrl.mianshi.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName AqsDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/20 9:19
 * @Version 1.0
 */
public class AqsDemo {

    public static void main(String[] args) {

        // 模拟3个顾客到银行办理业务

        //Aqs state+CLH队列  state=0 代表没有顾客 1 不能服务
        // CLH队列 双端队列 管理堵塞的线程

        // 源码分析Aqs怎么进行线程的管理和通知唤醒机制

        Lock lock = new ReentrantLock();

        Thread threadName1 = new Thread(() -> {

            lock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + "====come in ");
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } finally {
                lock.unlock();
            }

        }, "顾客A");
        threadName1.start();


        //由于只有一个窗口 只能等待在这
        Thread threadName2 = new Thread(() -> {

            lock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + "====come in ");

            } finally {
                lock.unlock();
            }

        }, "顾客B");
        threadName2.start();


        //由于只有一个窗口 只能等待在这
        Thread threadName3 = new Thread(() -> {

            lock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + "====come in ");

            } finally {
                lock.unlock();
            }

        }, "顾客C");
        threadName3.start();

    }


}
