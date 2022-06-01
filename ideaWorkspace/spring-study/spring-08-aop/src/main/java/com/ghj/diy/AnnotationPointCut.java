package com.ghj.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@EnableAspectJAutoProxy
@Aspect
@ComponentScan(basePackages = "com.ghj")
public class AnnotationPointCut {

    @Pointcut(value = "execution(* com.ghj.service.UserServiceImpl.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("注解方式==>==========执行前===========");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("注解方式==>==========执行后============");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("环绕前");
        //执行方法
        Object proceed = jp.proceed();

        //获得方法的签名
        System.out.println("获得方法的签名==>" + jp.getSignature());
        System.out.println("环绕后");
    }
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void afterReturn(Object result){
        System.out.println("方法返回值是：" + result);
    }
}
