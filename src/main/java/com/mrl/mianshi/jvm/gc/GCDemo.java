package com.mrl.mianshi.jvm.gc;

import java.util.Random;

/**
 * @ClassName GCDemo
 * @Description
 *
 * 七大垃圾收集器：
 *  * 1、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  (新生代和老年代都用Serial串行垃圾收集器)
 *    2、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC  (新生代：UseParNewGC 老年代 SerialOld)  ==>Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *    ------------------------------------------------------------------------------------------------------------------------------------
 *    3、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  (新生代和老年代都用并行收集器  java8默认  串行收集器在新生代和老年代的并行化 关注吞吐量   用户运行代码/(用户运行代码+垃圾收集)
      4、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (新生代和老年代都用并行收集器，同3)
 *    5、不加就是默认 默认就是3  -XX:+UseParallelGC
 *    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 *    6、CMS （用在老年代） 并发标记清除 节约空间，产生内存碎片  是一种获取最短回收停顿时间为目标的收集器,适合互联网站,重视相应速度
 *              1、先标记出需要回收的对象
 *              2、统一回收这些对象
 *       怎么开启：-XX:+UseConcMarkSweepGC
 *              开启后自动将 -XX:+UseParNewGC 打开
 *              开启后：使用ParNewG(Young区用)+CMS(Old区用）+SerialOld的收集器组合，SerialOld作为CMS出错的备用收集器
 *
 *         步骤：a、初始标记（标记一下GCRoots能直达的对象 停止所有用户线程
 *              b、并发标记 （和用户线程一起  从第一部标记的对象出发，并发标记可达对象
 *              c、重新标记 （CMS Remark 停止所有用户线程  修正对象关系变化以及新创建的对象
 *              d、并发清除 （和用户线程一起  基于标记对象进行清理
 *
 *
 *      优点 ：并发收集低停顿
 *      缺点：并发执行CPU压力大
 *            采用标记清除会产生较大的内存碎片
 *
 *    --------------------------------------------------------------------------------------------------------
 *
 *    7、SerialOld 单线程收集器 同1  SerialOld作为CMS出错的备用收集器  实际不用  了解
 *
 *   ================================================================================================================================================
 *
 *    G1 垃圾收集器  横跨年轻代 和 老年代  最大好处：化整为零，避免全内存扫描 只需要按照区域来扫描即可
 *    -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 *
 *    步骤：1、初始标记
 *          2、并发标记
 *          3、最终标记
 *          4、筛选回收
 *
 *
 *   ================================================================================================================================================================================================
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @Author lwq
 * @Date 2021/2/19 14:24
 * @Version 1.0
 */
public class GCDemo {

    public static void main(String[] args) {
        System.out.println("=============GcDemo===============");
        try {
            String str = "MRL";
            while (true){
                str+=str+new Random().nextInt(7777777)+new Random().nextInt(8888888);
                str.intern();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
