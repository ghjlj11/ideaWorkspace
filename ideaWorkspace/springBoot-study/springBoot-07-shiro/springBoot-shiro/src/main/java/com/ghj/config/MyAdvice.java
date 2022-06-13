package com.ghj.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class MyAdvice {

    Logger logger = Logger.getLogger(MyAdvice.class);

    @Pointcut("execution(* com.ghj.controller.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        logger.info("方法执行前");
    }
}
