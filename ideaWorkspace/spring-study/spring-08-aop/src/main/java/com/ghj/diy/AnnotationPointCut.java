package com.ghj.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author 86187
 */

@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("注解方式==>==========执行前===========");
    }

    @After("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("注解方式==>==========执行后============");
    }

    @Around("execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("环绕前");
        //执行方法
        Object proceed = jp.proceed();

        //获得方法的签名
        System.out.println("获得方法的签名==>" + jp.getSignature());
        System.out.println("环绕后");
    }
}
