package com.mrl.mianshi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProductConsumerDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/15 1:16
 * @Version 1.0
 */

public class ProductConsumerDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                shareResource.increase();
            }, String.valueOf(i + 1)).start();
        }


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                shareResource.decrease();

            }, String.valueOf(i + 1)).start();
        }



    }

}


class ShareResource{
    private Integer num = 0;

    private Lock lock = new ReentrantLock();

    public synchronized void increase(){

        //判断
        while (num==1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //干活
        num++;
        System.out.println(Thread.currentThread().getName()+"\t"+num);

        //通知
        this.notifyAll();
    }

    public synchronized void decrease(){

        //判断
        while (num==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        num--;
        System.out.println(Thread.currentThread().getName()+"\t"+num);

        this.notifyAll();

    }


}
