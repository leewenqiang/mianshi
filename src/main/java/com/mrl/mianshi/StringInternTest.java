package com.mrl.mianshi;

/**
 * @ClassName StringInternTest
 * @Description string itern
 * @Author lwq
 * @Date 2021/2/3 17:28
 * @Version 1.0
 */
public class StringInternTest {

    public static void main(String[] args) {

        String str1 = new StringBuilder("ali").append("baba").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1==str1.intern());


        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2==str2.intern());

    }

}
