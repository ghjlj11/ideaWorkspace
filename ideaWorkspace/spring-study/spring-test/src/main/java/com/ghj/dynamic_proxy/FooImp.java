package com.ghj.dynamic_proxy;

/**
 * @author 86187
 */
public class FooImp implements Foo, Koo{

    @Override
    public void add(int i, int j) {
        int k = i + j;
        System.out.println("结果为： " + k);
    }
    public void isM(String name){
        System.out.println("is me " + name);
    }

    @Override
    public void kkk(String a, String b) {
        System.out.println(a + "......" + b);
    }
}
