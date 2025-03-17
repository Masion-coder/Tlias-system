package com.tlias.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tlias.mapper.OperateLogMapper;
import com.tlias.model.OperateLog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;// 假设这是你的数据访问层接口

    @Around("@annotation(com.tlias.annotations.Log)")
    public Object logoperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 构建日志实体
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        // 保存日志
        log.info("记录日志操作：{}", operateLog);
        operateLogMapper.insert(operateLog);
        return result;
    }

    private Integer getCurrentUserId() {
        // 实现获取当前登录用户的逻辑
        // 这个方法需要根据你的安全框架或上下文来实现
        return 1;// 示例返回值
    }
}
