package com.east.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableAsync
@Slf4j
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class DemoApplication {

    public static void main(String[] args) {
        log.info("DemoApplication start!");
        SpringApplication.run(DemoApplication.class, "this is main function param1");
    }

}
