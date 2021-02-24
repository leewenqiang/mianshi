package com.mrl.mianshi.volatiletest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VolatileDemo
 * @Description java 虚拟机的 轻量级同步机制
 * 基本上遵守了jmm的规范
 * 可见性 禁止指令重新 不保证原子性
 * @Author lwq
 * @Date 2021/2/7 22:56
 * @Version 1.0
 */

class MyData {
    public volatile Integer num = 0;

    public void setNum100() {
        this.num = 100;
    }

    public  void addPlusPlus() {
        this.num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus2(){
        atomicInteger.getAndIncrement();
    }

}


public class VolatileDemo {
    public static void main(String[] args) {

        // seeOkVolitale();
//        noAyotmic();


        //atomicinteger();

    }

    private static void atomicinteger() {
        MyData myData = new MyData();

        long l = System.currentTimeMillis();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus2();
                }
            }, String.valueOf(i + 1)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+" \t num="+myData.atomicInteger.intValue());

        //6814
        System.out.println("time:"+(System.currentTimeMillis() - l));
    }

    /**
     * 不保证原子性
     */
    private static void noAyotmic() {


        MyData myData = new MyData();

        long l = System.currentTimeMillis();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i + 1)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+" \t num="+myData.num);

        //6814
        System.out.println("time:"+(System.currentTimeMillis() - l));
    }

    /**
     * 保证可见性
     */
    private static void seeOkVolitale() {
        MyData myData = new MyData();

        Thread threadName = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in ");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.setNum100();
            System.out.println(Thread.currentThread().getName() + " update the value \t" + myData.num);
        }, "A");
        threadName.start();

        while (myData.num == 0) {

        }

        System.out.println(Thread.currentThread().getName() + " 执行结束..." + myData.num);
    }

}

