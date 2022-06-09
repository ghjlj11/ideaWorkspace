package com.ghj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.tinylog.Logger;

/**
 * @author 86187
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.ghj.controller.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Logger.info(joinPoint.getTarget() + "开始了方法" + joinPoint.getSignature() );
        Object proceed = joinPoint.proceed();

        Logger.info(joinPoint.getTarget() + "的方法" + joinPoint.getSignature() + "返回了" + proceed);
        return proceed;
    }
}
