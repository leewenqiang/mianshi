package com.mrl.mianshi.threadpooltest;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CommonThreadFactory
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/7 11:58
 * @Version 1.0
 */
public class CommonThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在 jstack 问题排查时，非常有帮助
    CommonThreadFactory(String whatFeaturOfGroup) {
        namePrefix = "From CommonThreadFactory's " + whatFeaturOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        return thread;
    }
}
