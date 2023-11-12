package com.east.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@MapperScan(basePackages = "com.east.demo.persist.mapper",annotationClass = Mapper.class)
@EnableAsync
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        log.info("DemoApplication start!");
        SpringApplication.run(DemoApplication.class, "this is main function param1");
    }

}
