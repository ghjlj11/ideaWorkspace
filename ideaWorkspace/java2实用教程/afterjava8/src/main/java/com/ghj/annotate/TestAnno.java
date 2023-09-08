package com.ghj.annotate;

/**
 * @author guohuanjun1
 * @date 2023/8/4 17:27
 */
public class TestAnno {
    public static void main(String[] args) {
        TestAnno testAnno = new TestAnno();
        testAnno.test01(null);
    }
    public void test01(@ParamNotNull("not null") String a){
        System.out.println(a);
    }
}
