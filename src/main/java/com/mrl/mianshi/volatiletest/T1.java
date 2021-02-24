package com.mrl.mianshi.volatiletest;

/**
 * @ClassName T1
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/7 23:35
 * @Version 1.0
 */
public class T1 {

    volatile Integer num = 0;


    public void add() {
        num++;
    }
}
