package com.east.demo.service.util.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AsyncDemoServiceTest {
    @Autowired
    private AsyncDemoService asyncDemoService;


    @BeforeEach
    void setUp() {
//        this.asyncDemoService=new AsyncDemoService();
    }

    /**
     * 这里测试自定义拒绝策略中使用阻塞队列限制生产者速度方法
     * 当线程池满了后（核心线程和队列都满了）阻塞队列会阻塞主线程，直到线程池可以再放入任务
     *
     * @throws InterruptedException error
     */
    @Test
    void asyncRejectBlockingFuncTest() throws InterruptedException {
        log.info("this is main thread: {}", Thread.currentThread().getName());
        log.info("放入第1个任务");
        asyncDemoService.asyncRejectBlockingFunc();
        log.info("放入第2个任务");
        asyncDemoService.asyncRejectBlockingFunc();
        log.info("放入第3个任务");
        asyncDemoService.asyncRejectBlockingFunc();
        log.info("放入第4个任务");
        asyncDemoService.asyncRejectBlockingFunc();
        log.info("放入第5个任务");
        asyncDemoService.asyncRejectBlockingFunc();


        Thread.sleep(20 * 1000);
        log.info("主线程结束");
    }

    /**
     * 这里测试自定义拒绝策略中直接抛出异常，自身一个，队列一个，放入第三个任务就会抛出异常
     *
     * @throws InterruptedException error
     */
    @Test
    void asyncRejectThrowFuncTest() throws InterruptedException {
        log.info("this is main thread: {}", Thread.currentThread().getName());

        for (int i = 1; i < 6; i++) {
            log.info("放入第{}个任务", i);
            asyncDemoService.asyncRejectThrowFunc();
        }

        Thread.sleep(20 * 1000);
        log.info("主线程结束");
    }

    @Test
    void asyncTransactionalInMultiThread() {
        asyncDemoService.asyncTransactionalUsage();
    }
}