package com.mrl.mianshi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/5 12:02
 * @Version 1.0
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙....");
        });


        for (int i = 0; i < 7; i++) {
            Thread threadName = new Thread(() -> {

                 System.out.println(Thread.currentThread().getName()+"\t集龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i));
            threadName.start();
        }

    }

}
