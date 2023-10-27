package com.east.demo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author: east
 * @date: 2023/10/27
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(public * com.east.demo.controller..*.*(..))")
    public void log() {
    }
}
