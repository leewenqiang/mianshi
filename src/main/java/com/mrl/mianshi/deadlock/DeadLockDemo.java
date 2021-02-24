package com.mrl.mianshi.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DeadLockDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/15 20:51
 * @Version 1.0
 */
public class DeadLockDemo {

    public static void main(String[] args) {


        Thread threadName = new Thread(new MyRessource("A","B"), "AAA");
        Thread threadName2 = new Thread(new MyRessource("B","A"), "BBB");
        threadName.start();
        threadName2.start();

    }
}


class MyRessource implements Runnable{

    private String lockA;
    private String lockB;


    public MyRessource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA){
             System.out.println(Thread.currentThread().getName()+"\t "+"准备获取"+lockB);
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             synchronized (lockB){
                 System.out.println(Thread.currentThread().getName()+"\t "+"准备获取"+lockA);
             }
        }

    }
}
