package com.mrl.mianshi.spring;

import org.springframework.stereotype.Component;

/**
 * @ClassName B
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/20 16:00
 * @Version 1.0
 */
//@Scope("prototype")
@Component
public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B() {
        System.out.println("B执行");
    }
}
