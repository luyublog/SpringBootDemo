package com.east.demo.service.util.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步相关函数
 *
 * @author: east
 * @date: 2023/11/12
 */

@Slf4j
@Service
public class AsyncDemoService {
    @Async(value = "myBlockingExecutorPool")
    public void asyncRejectBlockingFunc() throws InterruptedException {
        log.info("异步方法执行，当前线程：{}", Thread.currentThread().getName());
        Thread.sleep(5 * 1000);
    }

    @Async(value = "myDefaultExecutorPool")
    public void asyncRejectThrowFunc() throws InterruptedException {
        log.info("异步方法执行，当前线程：{}", Thread.currentThread().getName());
        Thread.sleep(5 * 1000);
    }
}
