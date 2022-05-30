package com.ghj.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author 86187
 */
public class Log implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("advisor==>" + target.getClass().getName() + "执行了" + method.getName() + "方法");
    }
}
