package com.mrl.mianshi;

import java.util.concurrent.*;

/**
 * @ClassName CallAbleTest
 * @Description get方法放在最后一行
 *              一个 futuretask 多个线程调用 只会被调用一次
 * @Author lwq
 * @Date 2021/2/5 10:21
 * @Version 1.0
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testCountDowmLactch();

        //信号量  Semaphore 抢车位
        Semaphore semaphore = new Semaphore(3);

         //6个车抢3个资源

        for (int i = 1; i <= 6; i++) {
            Thread threadName = new Thread(() -> {

                //停车
                try {
                    semaphore.acquire();
                     System.out.println(Thread.currentThread().getName()+"\t占用了车位");
                     Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            }, "线程"+i);
            threadName.start();
        }


    }

    private static void testCountDowmLactch() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            Thread threadName = new Thread(() -> {
                 System.out.println(Thread.currentThread().getName()+"\t离开教室");
                startSignal.countDown();
            }, String.valueOf(i));
            threadName.start();
        }
        startSignal.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }

    private static void testFutureTask() throws InterruptedException, ExecutionException {
        MyThread2 myThread2 = new MyThread2();
//        new Thread(myThread2).start();

        //  1加到10
        //  2加到20
        //  3加到100

//         System.out.println(Thread.currentThread().getName());
        FutureTask<Integer> futureTask = new FutureTask<>(myThread2);

        new Thread(futureTask).start();
        new Thread(futureTask).start();


        System.out.println("main 计算完成");

        System.out.println(futureTask.get());
    }

}


class MyThread implements Runnable {
    @Override
    public void run() {

        System.out.println("come in ");
    }
}

class MyThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("call come in ");
        Thread.sleep(2000);
        return 1024;
    }
}


