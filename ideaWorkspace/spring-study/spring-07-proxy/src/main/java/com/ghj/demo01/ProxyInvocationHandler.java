package com.ghj.demo01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 86187
 */
public class ProxyInvocationHandler implements InvocationHandler {

    Object target ;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log(method.getName());
        Object result =method.invoke(target, args);
        return result;
    }
    public void log(String name){
        System.out.println("执行了" + name + "方法");
    }
}
