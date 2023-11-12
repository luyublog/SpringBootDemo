package com.east.demo.service.commonrecord.extra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * springboot启动后自动运行类
 *
 * @author: east
 * @date: 2023/11/12
 */

@Slf4j
@Component
@Order(value = Ordered.LOWEST_PRECEDENCE - 1)
public class MyApplicationRunner implements ApplicationRunner {
    /**
     * @param args args
     */
    @Override
    public void run(ApplicationArguments args) {
        log.info("this is MyApplicationRunner output, param is: {}", args.getNonOptionArgs().get(0));
    }
}
