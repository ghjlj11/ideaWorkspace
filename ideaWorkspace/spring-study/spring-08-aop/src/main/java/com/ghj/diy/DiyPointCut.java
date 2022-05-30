package com.ghj.diy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author 86187
 */
public class DiyPointCut {

    public void before(){
        System.out.println("before==>==========执行方法之前===========");
    }
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around.before:" + joinPoint.getSignature().getName() + "执行了");
        Object proceed = joinPoint.proceed();
        System.out.println("around.after:" + joinPoint.getSignature().getName() + "返回了" + proceed);
    }

    public void after(){
        System.out.println("after==>===========执行方法之后===========");
    }

    public void afterReturn(JoinPoint joinPoint,Object result){
        System.out.println("around.afterReturn:" + "返回了" + result);
    }
}
