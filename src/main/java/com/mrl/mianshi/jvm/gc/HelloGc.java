package com.mrl.mianshi.jvm.gc;

/**
 * @ClassName HelloGc
 * @Description
 *
 *
 *
 * @Author lwq
 * @Date 2021/2/15 23:10
 * @Version 1.0
 */
public class HelloGc {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("*****************HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
        //-XX:+PrintGCDetails -XX:MetaspaceSize=100m

//        byte[] b = new byte[50*1024*1024];
//

//        //虚拟机内存总量
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        //虚拟机使用的最大内存
//        long maxMemory = Runtime.getRuntime().maxMemory();
//
//        System.out.println("xms"+totalMemory+"字节,"+(totalMemory/(double)1024/1024)+"MB");
//        System.out.println("xmx"+maxMemory+"字节"+maxMemory/(double)1024/1024+"MB");





    }
}
