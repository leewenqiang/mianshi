package com.mrl.mianshi.jvm;

/**
 * @ClassName HelloTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/7 15:39
 * @Version 1.0
 */
public class HelloTest {

    public static void main(String[] args) {
        Object object = new Object();

//        System.out.println(Object.class.getClassLoader());


        /**
         * 测试
         */



        System.out.println(object.getClass().getClassLoader());


        Hello1 hello1 = new Hello1();
        System.out.println(hello1.getClass().getClassLoader());
        System.out.println(hello1.getClass().getClassLoader().getParent());
        System.out.println(hello1.getClass().getClassLoader().getParent().getParent());


        //双亲委派机制
        //除了事情  往上捅

        // 1、需要加载一个类 自己先不加载
        // 先去找最顶层 rt.jar去找  找到了直接用 找不到再往下找  找不到在往下找 App 找到了用 找不到ClassNotFound


        //沙箱安全机制
        // 防止伪造和jdk的相同包名的类




    }


}

class  Hello1{

}