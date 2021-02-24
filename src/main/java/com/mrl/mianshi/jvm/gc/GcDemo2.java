package com.mrl.mianshi.jvm.gc;

import java.util.Random;

/**
 * @ClassName GcDemo2
 * @Description  CPU飙升处理
 * @Author lwq
 * @Date 2021/2/19 16:54
 * @Version 1.0
 */
public class GcDemo2 {

    public static void main(String[] args) {

        while (true){
            System.out.println(new Random().nextInt(111111111));
        }

    }

}
