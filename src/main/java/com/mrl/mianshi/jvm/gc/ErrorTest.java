package com.mrl.mianshi.jvm.gc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ErrorTest
 * @Description 常见虚拟机错误
 * @Author lwq
 * @Date 2021/2/18 17:03
 * @Version 1.0
 */
public class ErrorTest {


    public static void main(String[] args) {
        //1、StackOverFlow 错误 栈溢出
//        testStackOverFlowErr();

        //2、OutOfMemoryError:java Heap Space
//        testOutOfMemoryErrorHeapSpace();



         //GC收集过度 但是效果很差 不进行继续收集
        //java.lang.OutOfMemoryError: GC overhead limit exceeded
//        testGcOverHeadLimitExcedded();

        //java.lang.OutOfMemoryError:Direct Buffer memory

        //直接内存溢出
//        testOutOfMemoryErrorDirectMembory();

        //高并发下 线程创建失败 和平台有关 在linux以
        // unable to create a native thread
//        testUnableTOCreateNativeTheread();


        // MetaSpace 加载类过多 导致出现元空间溢出
        // 元空间内容：加载的类信息 常量池（非字符串常量池） 静态变量 及时编译的代码段


    }

    private static void testUnableTOCreateNativeTheread() {

        int i = 0;
        while (true){

            try {
                new Thread(() -> {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, ""+(i++)).start();

                System.out.println("i=="+i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     */
    private static void testOutOfMemoryErrorDirectMembory() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);

    }

    /**
     * 参数 -Xms10m -Xmx10m -XX:+PrintGCDetails
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     */
    private static void testGcOverHeadLimitExcedded() {

        int i = 0;
        List<String> list = new ArrayList<>();

        while (true){
            try {
                list.add(String.valueOf(i++).intern());
            } catch (Exception e) {
                System.out.println("次数==="+i);
                e.printStackTrace();
                throw e;
            } finally {


            }
        }

    }


    /**
     * 参数 -Xms10m -Xmx10m -XX:+PrintGCDetails
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    private static void testOutOfMemoryErrorHeapSpace() {
        byte[] bytes = new byte[20*1024*1024];
    }

    /**
     * @Author lwq
     * @Date 2021/2/18 17:05
     * @Description  参数： Xss1024k 可以不加
     * @param
     * @return void
     **/
    private static void testStackOverFlowErr() {

        testStackOverFlowErr();

    }


}
