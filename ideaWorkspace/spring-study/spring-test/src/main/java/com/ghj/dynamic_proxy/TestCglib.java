package com.ghj.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 86187
 */
public class TestCglib {
    public static void main(String[] args) {
        FooImp fooImp = new FooImp();
        Class[] interfaces = new Class[]{ Foo.class, Koo.class};
        System.out.println("fooImp的接口==>" + Arrays.toString(interfaces));
        System.out.println("fooImp的接口==>" + Arrays.toString(fooImp.getClass().getInterfaces()));
        Object o = Enhancer.create(fooImp.getClass(), interfaces,  new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行之前啊");
                Object invoke = method.invoke(fooImp, args);
                System.out.println("执行完了啊");
                return invoke;
            }
        });
        System.out.println("o的class==>"+o.getClass());
        System.out.println("o的父类==>"+o.getClass().getSuperclass());
        System.out.println("o的接口==>"+Arrays.toString(o.getClass().getInterfaces()));
        System.out.println("--------------------------------");
        if(o instanceof Foo){
            System.out.println("是属于Foo这个接口的");
        }
        if(o instanceof Koo){
            System.out.println("是属于Koo这个接口的");
        }
        if(o instanceof FooImp f){
            System.out.println("----------");
            f.kkk("a", "b");
            f.isM("hahah");
        }
    }
}
