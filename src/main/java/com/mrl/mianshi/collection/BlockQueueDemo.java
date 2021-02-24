package com.mrl.mianshi.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BlockQueueDemo
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/5 17:26
 * @Version 1.0
 */
public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {


        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));

//      System.out.println(blockingQueue.add("d"));  Exception in thread "main" java.lang.IllegalStateException: Queue full



//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());  Exception in thread "main" java.util.NoSuchElementException


        //检查队首
//        System.out.println(blockingQueue.element());



//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));

//        System.out.println(blockingQueue.offer("c"));  false


//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());  null

//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
////        blockingQueue.put("d");  //阻塞 直到腾出位置
//
//
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
////        System.out.println(blockingQueue.take()); //阻塞
//
////        Thread.sleep(4000);
////        blockingQueue.put("c");


        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("a", 3, TimeUnit.SECONDS));


    }

}


