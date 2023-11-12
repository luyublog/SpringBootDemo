package com.east.demo.common.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 自定义线程池
 * 参考https://www.cnblogs.com/qfdy123/p/16832457.html
 * <p>
 * 对于CPU密集型任务，最大线程数是CPU线程数+1。对于IO密集型任务，尽量多配点，可以是CPU线程数*2，或者CPU线程数/(1-阻塞系数)。
 *
 * @author: east
 * @date: 2023/11/12
 */

@Configuration
@ConfigurationProperties(prefix = "config.thread-pool") // 不能是驼峰形式...
public class MyExecutorPool {
    @Value("${config.thread-pool.coreSize:8}")
    private int corePoolSize;

    @Value("${config.thread-pool.maxSize:32}")
    private int maxPoolSize;

    @Value("${config.thread-pool.queueCapacity:1024}")
    private int queueCapacity;

    @Value("${config.thread-pool.keepAlive:30}")
    private int keepAliveSeconds;

    @Value("${config.thread-pool.threadNamePrefix:myDefaultExecutorPool-}")
    private String threadNamePrefix;

    /**
     * 返回java提供的线程池，采用阻塞队列方案
     *
     * @return ExecutorService
     */
    @Bean(name = "myBlockingExecutorPool")
    public ExecutorService myJavaExecutorPool() {
        return new ThreadPoolExecutor(
                1,
                1,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                ThreadFactoryBuilder.create().setNamePrefix("myBlockingExecutorPoolPrefix-").build(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                // 这里用阻塞队列来实现生产者的堵塞
                                // throw new RuntimeException(e); 异常就不能抛出了
                            }
                        }
                    }
                });
    }

    /**
     * 返回java提供的线程池，采用普通抛出异常方案
     *
     * @return ExecutorService
     */
    @Bean(name = "myDefaultExecutorPool")
    public ExecutorService myJavaDefaultExecutorPool() {
        return new ThreadPoolExecutor(
                1,
                1,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                ThreadFactoryBuilder.create().setNamePrefix(threadNamePrefix).build(),
                new ThreadPoolExecutor.AbortPolicy());
//        return new ThreadPoolExecutor(
//                corePoolSize,
//                maxPoolSize,
//                keepAliveSeconds,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(queueCapacity),
//                ThreadFactoryBuilder.create().setNamePrefix(threadNamePrefix).build(),
//                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 返回springboot默认的线程池
     *
     * @return 默认的线程池
     */
    @Bean
    public ExecutorService springDefaultExecutorPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor.getThreadPoolExecutor();
    }

}
