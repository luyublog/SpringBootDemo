package com.east.demo.service.util.async;

import cn.hutool.core.lang.Assert;
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
     * 首先分析一下：这些异步线程肯定要阻塞子线程，如果不阻塞，主线程已经跑完了（todo 如果跑完了主事务会提交吗？）
     * 如果提交了则这个问题就没有意义了
     * 如果没提交子线程还异步长时间跑，这个主线程事务就会变得很大，这个本身就是问题。
     * 如果通过Future类型返回阻塞住主线程，想要子事务回滚影响主事务，子事务使用Required或者Support就行
     * <p>
     * todo 多线程环境@Transactional注解不会生效？？
     */
    @Transactional(rollbackFor = Exception.class)
    public void asyncTransactionalUsage() {

        databaseOperation.updateById(100L, "2");

        databaseOperation.asyncUpdateById(100L, "1");

        databaseOperation.asyncUpdateByIdWithTransactional(100L, "3");

    }

}
