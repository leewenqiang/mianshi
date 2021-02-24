package com.mrl.mianshi.volatiletest;

/**
 * @ClassName MySingleTonInstance
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/8 9:05
 * @Version 1.0
 */
public class MySingleTonInstance {

    public static void main(String[] args) {

//        System.out.println(SinleTonInsatnce.getInsatnce()==SinleTonInsatnce.getInsatnce());
//        System.out.println(SinleTonInsatnce.getInsatnce()==SinleTonInsatnce.getInsatnce());
//        System.out.println(SinleTonInsatnce.getInsatnce()==SinleTonInsatnce.getInsatnce());
//        System.out.println(SinleTonInsatnce.getInsatnce()==SinleTonInsatnce.getInsatnce());


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SinleTonInsatnce.getInsatnce();
            }, String.valueOf(i + 1)).start();
        }


    }

}


class SinleTonInsatnce{
    private static volatile SinleTonInsatnce insatnce = null;

    private SinleTonInsatnce(){
         System.out.println(Thread.currentThread().getName()+"\t excute the construct");
    }

    /**
     * @Author lwq
     * @Date 2021/2/8 9:09
     * @Description 这种情况会出现问题 要用双端检锁机制  在 synchronized 前后均加上判断
     *              但是也不能完全保证正确 （因为存在指令重排序问题）
     *                 insatnce = new SinleTonInsatnce();这行代码
     *                 1、分配内存
     *                 2、初始化对象
     *                 3、将对象赋值给引用变量 insatnce
     *                 2和3没有数据依赖关系（可能会导致指令重排，3先执行，导致实例变量还未初始化完成就被其他线程拿到从而出现拿到的是一个null对象，导致线程安全问题
     *                 解决方案：加上volitale 禁止指令重排
     * @param
     * @return com.mrl.mianshi.volatiletest.SinleTonInsatnce
     **/
    public static SinleTonInsatnce getInsatnce(){
        if(insatnce==null){
            synchronized (SinleTonInsatnce.class){
                if(insatnce==null){
                    insatnce = new SinleTonInsatnce();
                }
            }

        }
        return insatnce;
    }

}

