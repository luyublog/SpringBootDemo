package com.east.demo.service.util.async;

import cn.hutool.core.lang.Assert;
import com.east.demo.common.exception.BaseException;
import com.east.demo.common.exception.ErrorEnum;
import com.east.demo.service.commonrecord.mybatis.DatabaseOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 异步相关函数
 *
 * @author: east
 * @date: 2023/11/12
 */

@Slf4j
@Service
public class AsyncDemoService {

    private final DatabaseOperation databaseOperation;

    @Autowired
    public AsyncDemoService(DatabaseOperation databaseOperation) {
        Assert.isFalse(Objects.isNull(databaseOperation));
        this.databaseOperation = databaseOperation;
    }

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

    /**
     * 测试多线程环境下@Transactional注解传播情况
     * REQUIRED: 默认，加入，没有则自建
     * REQUIRES_NEW： 完全独立，既不影响外部事务，也不受外部事务影响
     * NESTED: 嵌套，部分独立，不影响外部事务，但受外部事务影响（外部回滚它也回滚，但它回滚外部不回滚（除非异常带过去了)）
     * <p>
     * 遇到一个问题：说主线程进行一个删除操作，多个异步子线程执行插入操作，如果插入操作遇到异常回滚了，怎么让主线程也回滚
     * note 多线程环境@Transactional注解不会生效
     * 该注解通过threadLocal来进行传递，如果都不是一个线程当然也就传递不了了。就算用上注解和SUPPORT也没效果
     * 首先分析一下：这些异步线程肯定要阻塞主线程，如果不阻塞，主线程已经跑完了（note 如果跑完了主事务会提交吗？ 会，甚至会关闭连接，导致子事务连接失败?
     * 不用测试类，改用正常启动再试试，测试类结束后可能直接结束了）
     * 如果提交了则这个问题就没有意义了
     * 如果没提交子线程还异步长时间跑，这个主线程事务就会变得很大，这个本身就是问题。
     * 如果通过Future类型返回阻塞住主线程，想要子事务回滚影响主事务，获取子线程执行结果就行了，有异常的话子线程已经回滚了，主线程也跟着回滚就行，但如果
     * 很多子线程呢？1. 更改逻辑，子线程只处理数据然后返回给阻塞的主线程，由主线程统一提交 2. JTA 3.网上的TransactionSynchronizationManager（非常不推荐）
     * <p>
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void asyncTransactionalUsage() {

        // 无子线程，在当前事务内
        databaseOperation.updateById(100L, "2");

        // 异步线程，无事务
//        databaseOperation.asyncUpdateById(100L, "1");

        // 异步线程，手动添加事务，阻塞主线程获取结果
//        CompletableFuture<Void> myDefaultExecutorPool = CompletableFuture.runAsync(() -> {
//            databaseOperation.updateById(100L, "1");
//        }, SpringUtil.getBean("myDefaultExecutorPool"));
//        myDefaultExecutorPool.join();

        // 子线程支持前事务或无事务
//        databaseOperation.asyncUpdateByIdWithTransactional(100L, "3");

        // 子线程开新事务
//        databaseOperation.asyncUpdateByIdWithNewTransactional(100L, "3");


        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (true) {
            throw new BaseException(ErrorEnum.FAIL);
        }


    }

}
