package com.east.demo.common.config.middle.redis;

/**
 * Redis配置: 主要来控制开关
 * note: 不太好弄，session自动配置用到了redis.
 * RedisHttpSessionConfiguration这个类采用自动装配方法在启动时去获取RedisConnectionFactory并作为bean返回，其中getObject方法没找到会抛出异常
 * 没找到RedisHttpSessionConfiguration的取消配置方法
 *
 * @author: east
 * @date: 2023/11/19
 */

//@Configuration
//@ConditionalOnProperty(value="spring.redis.enable" , havingValue = "false")
public class RedisConfig {
}
