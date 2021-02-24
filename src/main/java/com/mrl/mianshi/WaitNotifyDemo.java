package com.mrl.mianshi;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName WaitNotifyDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/4 16:48
 * @Version 1.0
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {

        BizCode2 bizCode = new BizCode2();

        Thread threadName = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                    bizCode.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A");
        threadName.start();

        Thread threadName2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                    bizCode.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");
        threadName2.start();

        Thread threadName3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                    bizCode.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");
        threadName3.start();

        Thread threadName4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                    bizCode.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");
        threadName4.start();

    }
}

class BizCode {

    private Integer num = 0;

    public synchronized void increase() throws InterruptedException {
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }


    public synchronized void decrease() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }


}


class BizCode2 {

    private Integer num = 0;

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void increase() throws InterruptedException {

        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();

        } finally {
            lock.unlock();
        }


    }


    public void decrease() throws InterruptedException {

        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }


    }


}


class Demo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return null;
    }
}

