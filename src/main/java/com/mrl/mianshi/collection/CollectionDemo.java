package com.mrl.mianshi.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName ListDemo
 * @Description
 *
 * 1、故障现象
 *
 * 2、导致原因
 *
 * 3、解决方案
 *
 * 4、优化建议
 *
 * @Author lwq
 * @Date 2021/2/4 23:23
 * @Version 1.0
 */
public class CollectionDemo {

    public static void main(String[] args) {
//        unsafeList();

//        Set<String> set = new HashSet<>();

//        unsafeset();


//        Map<String,String> map = new HashMap<>();

        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            Thread threadName = new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i));
            threadName.start();
        }




    }

    private static void unsafeset() {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
           Thread threadName = new Thread(() -> {
               set.add(UUID.randomUUID().toString().substring(0,8));
               System.out.println(set);
           }, String.valueOf(i));
           threadName.start();
        }
    }

    private static void unsafeList() {
        //        List list = new ArrayList();

        List list = new CopyOnWriteArrayList();

        //Vector 线程安全
//        List list = new Vector();

//        List list = new CopyOnWriteArrayList();

//        List list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {

         new Thread(() -> {

             list.add(UUID.randomUUID().toString().substring(0,8));
             System.out.println(list);

           }, String.valueOf(i)).start();

        }
    }

}
