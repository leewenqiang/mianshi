package com.mrl.mianshi;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName com.mrl.mianshi.LockSupportTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/3 23:17
 * @Version 1.0
 */

//@SpringBootTest
public class LockSupportTest {

//    @Autowired
//    ApplicationContext applicationContext;

    @Test
    public void test1(){


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");

        applicationContext.getBean("a");
        applicationContext.getBean("b");

        System.out.println(1);
    }


}
