package com.mrl.mianshi.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/5 14:18
 * @Version 1.0
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {


        myCache myCache = new myCache();

        for (int i = 0; i <5; i++) {
            final  int temp = i;
            Thread threadName = new Thread(() -> {
                try {
                    myCache.put(temp+1+"",temp+1+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程"+i);
            threadName.start();
        }


        for (int i = 0; i <5; i++) {
            final  int temp = i;
            Thread threadName = new Thread(() -> {
                try {
                    myCache.get(temp+1+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程"+i);
            threadName.start();
        }

    }


}


class myCache {
    private Map<String, Object> cache = new HashMap<>();

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String k, Object v) throws InterruptedException {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t准备写入");
            TimeUnit.MILLISECONDS.sleep(100);
            cache.put(k, v);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }


    public void get(String k) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t准备读取");
            TimeUnit.MILLISECONDS.sleep(100);
            Object o = cache.get(k);
            System.out.println(Thread.currentThread().getName() + "\t读取完成"+o);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }



}
