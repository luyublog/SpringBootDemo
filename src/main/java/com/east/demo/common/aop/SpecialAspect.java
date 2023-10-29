package com.east.demo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 特殊切面
 *
 * @author: east
 * @date: 2023/10/29
 */
@Aspect
@Component
@Slf4j
public class SpecialAspect {

    @Pointcut("@annotation(com.east.demo.common.annotation.SpeicalAspectAnnotation)")
    public void mySpecialAspect() {
    }

    @Before("mySpecialAspect()")
    public void beforeCustomLogging(JoinPoint joinPoint) {
        log.info("Before the method {} executed I will do ...", joinPoint.getSignature().toShortString());
    }
}
