package com.tlias.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(1) // 优先级，值越小，优先级越高
public class RecondTimeAspect {
    @Around("execution(* com.tlias.service.impl.*.*(..))")
    public Object recondTime(ProceedingJoinPoint pjp) throws Throwable {
        // 1.记录方法运行的开始时间
        long begin = System.currentTimeMillis();

        // 2.执行目标方法
        Object result = pjp.proceed();

        // 3.记录方法运行的结束时间
        long end = System.currentTimeMillis();
        log.info("方法 {} 执行耗时时间：{}ms",pjp.getSignature() , end - begin);
        return result;
    }
}
