package com.mrl.mianshi.threadpooltest;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/7 10:14
 * @Version 1.0
 */
public class ThreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor executor = null;
        try {
            CommonThreadFactory factory =
                    new CommonThreadFactory("李文强测试");

             executor =
                    new ThreadPoolExecutor(2,
                            5, 2,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000),
                    factory,
                    new ThreadPoolExecutor.AbortPolicy());

            for (int i = 0; i < 10; i++) {

                executor.submit(()->{
                     System.out.println(Thread.currentThread().getName());
                });
            }
        } finally {
            executor.shutdown();
        }


        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return 100;
        }).whenCompleteAsync((t,u)->{
            System.out.println(t);
            int age = 10/0;
        }).exceptionally(u->{
            return 1;
        });

        System.out.println(integerCompletableFuture.get());


    }


}
