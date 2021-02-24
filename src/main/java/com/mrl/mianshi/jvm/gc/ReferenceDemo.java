package com.mrl.mianshi.jvm.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @ClassName ReferenceDemo
 * @Description
 *
 * 1、强引用 普通引用 只要对象可达（GCroots可达  GCroots四大类型 局部变量表 静态变量 常量 本地方法栈引用的对象
 *           就不会被Gc回收
 * 2、软引用
 *           当堆内存够用的时候不会被GC回收 否则则会被回收
 * 3、弱引用
 * 4、虚引用
 *
 * @Author lwq
 * @Date 2021/2/18 16:38
 * @Version 1.0
 */
public class ReferenceDemo {

    public static void main(String[] args) {

        //强引用
//        strongReference();

        //软引用
//        softReference();


        //虚引用  只要gc就回收
        //软引用 和虚引用可以用在做本地缓存上  当gc的时候自动回收 避免OOM
        // WeakHashMap
//        weakReferce();

//        weakReferceForWeakHashMap();


        //虚引用 监控对象被回收的时候 做的一些事情 和引用队列 ReferenceQueue 配合使用


    }

    private static void weakReferceForWeakHashMap() {

        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();

        Integer key = new Integer(1);
        String value = "WeakHashMap";

        weakHashMap.put(key,value);
        System.out.println("================="+weakHashMap+"=====size:"+weakHashMap.size());


        key=null;
        System.out.println("================="+weakHashMap+"=====size:"+weakHashMap.size());

        System.gc();
        System.out.println("================="+weakHashMap+"=====size:"+weakHashMap.size());

    }

    private static void weakReferce() {



        Object o1 = new Object();

        WeakReference<Object> o2 = new WeakReference<>(o1);

        System.out.println(o1);
        System.out.println(o2.get());

        o1 = null;

        System.gc();

        try {
            // byte[] bytes = new byte[10*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("o1=="+o1);
            System.out.println("o2=="+o2.get());
        }

    }

    private static void softReference() {



        Object o1 = new Object();

        SoftReference<Object> o2 = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(o2.get());

        o1 = null;


        try {
           // byte[] bytes = new byte[10*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("o1=="+o1);
            System.out.println("o2=="+o2.get());
        }




    }

    private static void strongReference() {

        Object o1 = new Object();

        Object o2 = o1;

        System.out.println(o1);
        System.out.println(o2);

        o1 = null;

        System.gc();


        System.out.println(o1);
        System.out.println(o2);





    }


}
