package com.mrl.mianshi.spring;

import org.springframework.stereotype.Component;

/**
 * @ClassName A
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/20 16:00
 * @Version 1.0
 */
//@Scope(value = "prototype")
@Component
public class A {


    private B b;


    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A() {
        System.out.println("A执行");
    }
}
