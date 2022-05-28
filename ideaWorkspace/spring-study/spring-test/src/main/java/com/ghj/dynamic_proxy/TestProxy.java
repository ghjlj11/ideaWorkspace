package com.ghj.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 86187
 */
public class TestProxy {
    public static void main(String[] args) {
        Foo ob = new FooImp();
        Object o = Proxy.newProxyInstance(ob.getClass().getClassLoader(), ob.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法执行前");
                Object invoke = method.invoke(ob, args);
                System.out.println("方法执行后");
                return invoke;
            }
        });
        if(o instanceof Foo f){
            f.add(1 ,2);
        }
    }
}
