package com.ghj.dynamic_proxy;

/**
 * @author 86187
 */
public class FooImp implements Foo{

    @Override
    public void add(int i, int j) {
        int k = i + j;
        System.out.println("结果为： " + k);
    }
}
