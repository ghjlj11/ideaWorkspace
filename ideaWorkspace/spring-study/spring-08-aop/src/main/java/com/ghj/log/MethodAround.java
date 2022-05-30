package com.ghj.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 86187
 */
public class MethodAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("---------------方法执行前---------------");
        Object proceed = invocation.proceed();
        System.out.println("---------------方法执行后---------------");
        return proceed;
    }
}
