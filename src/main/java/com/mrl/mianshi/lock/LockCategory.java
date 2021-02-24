package com.mrl.mianshi.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockCategory
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/8 14:50
 * @Version 1.0
 */
public class LockCategory {

    public static void main(String[] args) {


        Season.SPRING.getCode();

        //非公平锁 NonfairSync
        Lock lock = new ReentrantLock();
        // 非公平锁 和 公平锁
        // 非公平锁:抢占式的 抢占失败，再去排队
        // 公平锁：获得锁的顺序和申请锁的顺序一致

        //可重入锁
        //线程可以进入他拥有锁的同步代码块
        // 最大作用 避免死锁
        // sync void m1(){
        //  m2();
        // }


        //自旋锁
        // 理论 代码 小总结
        //尝试获取锁的线程不会被立即阻塞 采用循环的方式获取锁 好处不会阻塞  坏处：长时间不能获取锁 性能下降
        //手写一个自旋锁

        SpinLock spinLock = new SpinLock();

        Thread threadName = new Thread(() -> {
            spinLock.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLock.myUnlock();
        }, "A");
        threadName.start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread threadName2 = new Thread(() -> {
            spinLock.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLock.myUnlock();
        }, "B");
        threadName2.start();



    }



}

/**
 * 自旋锁
 */
class SpinLock{

    /**
     * 默认是null
     */
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();


    /**
     * 加锁方法
     */
    public void myLock(){
         System.out.println(Thread.currentThread().getName()+" \t 进入myLock，准备获取锁");

         //尝试将当前线程塞入atomicReference 期望值是null 如果成功 说明当前没有线程占用该锁
        // 不成功则一直尝试 设置
         while (!atomicReference.compareAndSet(null,Thread.currentThread())){

         }

    }

    /**
     * 解锁
     */
    public void  myUnlock(){
        System.out.println(Thread.currentThread().getName()+" \t 进入myUnlock，准备解锁");

        atomicReference.compareAndSet(Thread.currentThread(),null);

        System.out.println(Thread.currentThread().getName()+" \t 进入myUnlock，解锁成功");

    }


}