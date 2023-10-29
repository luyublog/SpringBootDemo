package com.east.demo.common.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    ThreadLocal<HashMap<String, Object>> hashMapThreadLocal = new ThreadLocal<>() {
    };
    /**
     * 切入点
     */
    @Pointcut("execution(public * com.east.demo.controller..*.*(..))")
    public void myLog() {
    }

    @Before("myLog()")
    // 这里用了ProceedingJoinPoint作为参数后，myLog就升级成了ProceedingJoinPoint，
    // 但是ProceedingJoinPoint只能在around使用，所以就会报错ProceedingJoinPoint is only supported for around advice
    // public void aroundLog(ProceedingJoinPoint joinPoint) {
    public void before(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        if (Objects.isNull(hashMapThreadLocal.get())) {
            hashMapThreadLocal.set(new HashMap<>());
        }
        hashMapThreadLocal.get().put("startTime", System.currentTimeMillis());

        log.info("接口：[{}] 接收到新的请求", Objects.isNull(annotation) ? "方法无描述注解" : annotation.value());
        log.info("接口uri: [{}]", request.getRequestURI());
        // todo 仅打印JSON报文
        log.info("{}类型 请求参数：{}", request.getMethod(), JSONUtil.toJsonStr(getNameAndValue(joinPoint)));

    }

    @AfterReturning(pointcut = "myLog()", returning = "methodResult")
    public void after(JoinPoint joinPoint, Object methodResult) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (Objects.isNull(methodResult)) {
            if (response.getContentType().equalsIgnoreCase("application/json;charset=UTF-8")) {
                try {
                    methodResult = "待定怎么获取void响应类型接口的数据";
//                    char[] jsonStr=new char[response.getBufferSize()];
//                    response.getWriter().write(jsonStr);
//                    methodResult=jsonStr;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 打印请求相关参数
        long startTime = (long) hashMapThreadLocal.get().get("startTime");
        // 解析用户浏览器相关信息
        UserAgent userAgent = UserAgentParser.parse(request.getHeader("User-Agent"));
        log.info("响应结果：{}", JSONUtil.toJsonStr(JSONUtil.toJsonStr(methodResult)));
        log.info("接口耗时：{} ms", System.currentTimeMillis() - startTime);
        log.info("调用方IP,OS,BROWSER：{},{},{}", getIp(request), userAgent.getOs().toString(), userAgent.getBrowser().toString());
    }

    /**
     * 获取方法参数名和参数值
     * 这里会以方法的形参：实际参数的格式打印，例如：{"request":{"demo":"test"}}
     * 其中request为@RequestBody上的参数名，{"demo":"test"}为具体报文。其实没必要，直接打印JoinPoint里面的args感觉就行了
     *
     * @param joinPoint 切点
     * @return 参数
     */
    private static Map<String, Object> getNameAndValue(JoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return new HashMap<>();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return new HashMap<>();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        final String UNKNOWN = "unknown";

        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    /**
     * 接口调用信息
     *
     * @author: east
     * @date: 2023/10/27
     */
    @Data
    @Builder
    static class LogInfo {
        // 线程id
//        private String threadId;
        // 线程名称
//        private String threadName;
        // 接口描述
        private String methodName;
        // 接口描述
//        private String description;
        // 操作系统
        private String os;
        // 浏览器
        private String browser;
        //        private String operation;
        // 接口耗时
        private Long spendTime;

        //        private String basePath;
        private String uri;
        //        private String url;
        private String contentType;
        // 接口类型
        private String methodType;
        // 源IP
        private String ip;
        // 请求参数
        private Object parameter;
        // 接口响应结果
        private Object result;

        public void beginPrint() {
            log.info("接口: {} 接收到 {} 类型请求", uri, methodType);
            log.info("接口描述：{}", methodName);
            // todo 仅打印JSON报文
            log.info("请求参数：{}", JSONUtil.toJsonStr(parameter));
        }

        public void endPrint() {
            // todo 仅打印JSON报文
            log.info("响应结果：{}", JSONUtil.toJsonStr(result));
            log.info("接口耗时：{} ms", spendTime);
            log.info("调用方IP,OS,BROWSER：{},{},{}", ip, os, browser);
        }
    }
}


