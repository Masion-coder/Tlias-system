package com.tlias.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(5) // 优先级，值越小，优先级越高
public class MyAspect {
    @Pointcut("execution(* com.tlias.service.impl.*.*(..))")
    public void pt(){}

    /*
     * 前置通知 - 目标方法执行前执行
     */
    @Before("pt()")
    public void before(JoinPoint jp){
        log.info("前置通知");
        // 1.获取目标对象
        log.info("获取目标对象：{}", jp.getTarget());

        // 2.获取目标类
        log.info("获取目标类：{}", jp.getTarget().getClass().getName());

        // 3.获取目标方法
        log.info("获取目标方法：{}", jp.getSignature().getName());

        // 4.获取目标方法的参数
        log.info("获取目标方法的参数：{}", Arrays.toString(jp.getArgs()));
    }

    /*
     * 环绕通知 - 目标方法执行前和执行后执行
     */
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        log.info("环绕通知前");
        Object result = pjp.proceed();
        log.info("环绕通知后"); 
        return result;
    }

    /*
     * 后置通知 - 目标方法执行后执行，无论方法是否异常
     */
    @After("pt()")
    public void after(){
        log.info("后置通知");
    }

    /*
     * 后置返回通知 - 目标方法正常返回后执行，如果方法有异常，则不执行
     */
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("后置返回通知");
    }

    /*
     * 后置异常通知 - 目标方法抛出异常后执行
     */
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("后置异常通知");
    }
}
