package com.mrl.mianshi.aqs;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockSupportDemo4
 * @Description
 *
 * wait和notify
 *  1、必须在 synchronized 代码块里
 *  2、将notify放在wait之前 无法唤醒线程
 *
 * await 和  signal
 * 1、必须在 lock 和 unlock 代码块里
 *  2、将await放在signal之前 无法唤醒线程
 *
 *  locksupport 可以解决上述问题
 *     无锁块要求
 *     可以先唤醒 后阻塞（等于没阻塞
 *
 *     直接阻塞线程 和 唤醒
 *
 * @Author lwq
 * @Date 2021/2/3 23:04
 * @Version 1.0
 */
public class LockSupportDemo4 {




    static Object lockA = new Object();

    static Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();


    public static void main(String[] args) {
        testlocksupport();


    }

    /**
     * locksupport
     */
    private static void testlocksupport() {
        //LockSupport  language is power
        Thread a = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"come in ");
            //阻塞线程
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"被唤醒...");

        }, "A");
        a.start();

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"come in ");

            //通知线程 唤醒线程
            LockSupport.unpark(a);

            System.out.println(Thread.currentThread().getName()+"唤醒线程a ");


        }, "B");
        b.start();
    }


    public void testWaitNotify(){


        new Thread(()->{

            synchronized (lockA){
                System.out.println(Thread.currentThread().getName()+"---come in ");
                try {
                    lockA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒，准备竞争锁");
            }

        },"A").start();


        new Thread(()->{

            synchronized (lockA){
                System.out.println(Thread.currentThread().getName()+"---进来了  ");

                lockA.notify();

                System.out.println(Thread.currentThread().getName()+"准备唤醒其他等待在锁上的线程");
            }

        },"B").start();
    }


    public void testAwaitSignal(){

        new Thread(()->{


            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"--进来了");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--被唤醒");
            }finally {
                lock.unlock();
            }

        },"A").start();

        new Thread(()->{

            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"--进来了");
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"--将要唤醒等待lock锁的线程");
            }finally {
                lock.unlock();
            }

        },"B").start();

    }



}
