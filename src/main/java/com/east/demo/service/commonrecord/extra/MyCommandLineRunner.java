package com.east.demo.service.commonrecord.extra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 随springboot启动运行类
 *
 * @author: east
 * @date: 2023/11/12
 */

@Slf4j
@Component
@Order(value = Ordered.LOWEST_PRECEDENCE - 2)
public class MyCommandLineRunner implements CommandLineRunner {
    /**
     * @param args args from main func
     */
    @Override
    public void run(String... args) {
        log.info("this is MyCommandLineRunner output, param is: {}", args[0]);
    }
}
