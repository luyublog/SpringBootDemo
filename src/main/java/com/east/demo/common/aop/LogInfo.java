package com.east.demo.common.aop;

import lombok.Data;

/**
 * 接口调用信息
 *
 * @author: east
 * @date: 2023/10/27
 */

@Data
public class LogInfo {
    private String description;
    private String username;
    private Long startTime;
    private String operation;
    private Long spendTime;
    private String basePath;
    private String uri;
    private String url;
    private String methodType;
    private String ip;
    // 参数
    private Object parameter;
    // 接口响应结果
    private Object result;
}
