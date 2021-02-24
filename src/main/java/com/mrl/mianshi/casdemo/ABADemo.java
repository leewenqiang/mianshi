package com.mrl.mianshi.casdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName ABADemo
 * @Description ABA 问题
 * 一个线程多次修改，最后修改的值和初始值一样
 * 其他线程CAS判断满足条件 去做修改（实际上已经被改掉了）
 * @Author lwq
 * @Date 2021/2/8 14:18
 * @Version 1.0
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("===============ABA问题==============");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "T1").start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicReference.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "\t now值为" + atomicReference.get());

        }, "T2").start();
        System.out.println("===============ABA问题==============");


        System.out.println("===============ABA问题解决方案==============");
        new Thread(() -> {
            int teamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 版本号:" + teamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 101, teamp, teamp + 1);

            System.out.println(Thread.currentThread().getName() + "\t 版本号:" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 版本号:" + atomicStampedReference.getStamp());


        }, "T3").start();


        new Thread(() -> {
            int teamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 版本号:" + teamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 2019, teamp, teamp + 1);
            System.out.println(atomicStampedReference.getStamp()+"==="+atomicStampedReference.getReference());
        }, "T4").start();

    }

}
