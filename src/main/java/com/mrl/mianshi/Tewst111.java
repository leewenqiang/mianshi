package com.mrl.mianshi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Tewst111
 * @Description 两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z,
 * 要求用线程间通信  12A34B56C
 * 3 6 9
 * @Author lwq
 * @Date 2021/2/4 14:15
 * @Version 1.0
 */
public class Tewst111 {


    private native void tewst111();

    static ExecutorService executorService1;
    static ExecutorService executorService2;


    static {
        executorService1 = Executors.newSingleThreadExecutor();
        executorService2 = Executors.newSingleThreadExecutor();
    }

    public static void main(String[] args) {


       /* PrintDemo printDemo = new PrintDemo();
        Thread threadName = new Thread(() -> {
            try {
                printDemo.dayinShuzi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "input yor threadname");
        threadName.start();

        Thread threadName2 = new Thread(() -> {
            try {
                printDemo.dayinZimu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "input yor threadname");
        threadName2.start();
*/

//        System.out.println(3%3);


        PrintDemo2 printDemo2 = new PrintDemo2();
        Thread AA = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                printDemo2.printAA();
            }

        }, "AA");
        AA.start();

        Thread BB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printDemo2.printBB();
            }
        }, "BB");
        BB.start();

        Thread CC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printDemo2.printCC();
            }
        }, "CC");
        CC.start();

    }

}

/**
 * 两个线程 打印出12A34B....5152Z
 */
class PrintDemo {

    private static int num = 1;


    public synchronized void dayinZimu() throws InterruptedException {
        for (char i = 'A'; i <= 'Z'; i++) {
            while (num % 3 != 0) {
                this.wait();
            }

            System.out.println(i + "");
            num++;

            this.notifyAll();
        }
    }


    public synchronized void dayinShuzi() throws InterruptedException {

        for (int i = 1; i <= 52; i++) {
            while (num % 3 == 0) {
                this.wait();
            }

            System.out.println(i + "");
            num++;

            this.notifyAll();
        }


    }

}


/**
 * AA打印5次 BB打印10次 CC打印15次
 *
 *
 *
 * 接着
 * AA打印5次 BB打印10次 CC打印15次
 *
 * 1 2 3 4 5 6 7 8 9
 * 1%3 1
 * 2%3 2
 * 3%3 0
 *
 * 打印10轮
 */

class PrintDemo2{

    Lock lock = new  ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    int num=1;
    int flag = 1;

    public void print(String str,int times){
        lock.lock();
        try {

            while (flag != 1){
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } finally {
            lock.unlock();
        }
    }


    public void printAA(){

        lock.lock();
        try {
            while (num%3!=1){
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i <= 5; i++) {
                 System.out.println(Thread.currentThread().getName()+" "+i);
            }
//            System.out.println("");

            num++;

            //通知打印BB的线程执行
            conditionB.signalAll();


        } finally {
            lock.unlock();
        }


    }


    public void printBB(){
        lock.lock();
        try {
            while (num%3!=2){
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            System.out.println();


            num++;

            //通知打印CC的线程执行
            conditionC.signalAll();


        } finally {
            lock.unlock();
        }
    }


    public void printCC(){
        lock.lock();
        try {
            while (num%3!=0){
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
           System.out.println();

            num++;

            //通知打印AA的线程执行
            conditionA.signal();


        } finally {
            lock.unlock();
        }
    }



}

