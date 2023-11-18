package com.east.demo.common.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 *
 * @author: east
 * @date: 2023/11/18
 */

@Slf4j
@Component
public class ScheduleTask {

    /**
     * Cron表达式是一种强大的用于配置定时任务的字符串格式，由六或七个空格分隔的字段组成，每个字段代表一个不同的时间单位。以下是Cron表达式的一些常见示例及其含义：
     * <p>
     * ### 基本格式
     * <p>
     * Cron表达式的基本格式通常为：`秒 分 时 日 月 周（年）`。年字段是可选的。
     * <p>
     * ### 示例
     * <p>
     * 1. **每分钟执行一次**：
     * ```
     * 0 * * * * *
     * ```
     * 解释：每小时的每分钟的第0秒执行。
     * <p>
     * 2. **每小时执行一次**：
     * ```
     * 0 0 * * * *
     * ```
     * 解释：每天的每小时的第0分钟第0秒执行。
     * <p>
     * 3. **每天特定时间执行**（例如，每天上午10:15执行）：
     * ```
     * 0 15 10 * * *
     * ```
     * 解释：每天的10:15:00执行。
     * <p>
     * 4. **每周特定时间执行**（例如，每周一上午10:15执行）：
     * ```
     * 0 15 10 * * MON
     * ```
     * 或者使用数字（1代表周一，7代表周日）：
     * ```
     * 0 15 10 * * 1
     * ```
     * 解释：每周一的10:15:00执行。
     * <p>
     * 5. **每月特定日期和时间执行**（例如，每月1日上午10:15执行）：
     * ```
     * 0 15 10 1 * *
     * ```
     * 解释：每月1日的10:15:00执行。
     * <p>
     * 6. **组合使用**（例如，每周一到周五，上午10:15执行）：
     * ```
     * 0 15 10 ? * MON-FRI
     * ```
     * 解释：每周一到周五的10:15:00执行。
     * <p>
     * 7. **每年特定时间执行**（例如，每年1月1日午夜执行）：
     * ```
     * 0 0 0 1 1 *
     * ```
     * 解释：每年1月1日的00:00:00执行。
     * <p>
     * ### 注意事项
     * <p>
     * - Cron表达式中月份和星期的计数通常是从1开始的（即1表示1月或周日）。
     * - “?”字符用于日和周字段，表示“没有特定的值”，通常用于其中一个字段，而另一个字段设置了具体的值。
     * - 在不同的系统或库中，Cron表达式的格式可能略有不同。以上示例适用于Spring的@Scheduled注解。
     * - 周与日通常互斥，出现一个值则另一个用？
     * <p>
     * Cron表达式提供了极大的灵活性来定义各种复杂的定时任务。
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void myScheduleTask1() {
        // 每5min运行一次
        log.info("每五分钟运行一次的自动任务执行了");
    }

    public void myScheduleTaskError() {
        // @Scheduled(cron = "* */5 * * * *")
        // 每5min运行60次 这里第一个*表示任意秒数，满足*/5的这个分钟内，每秒都会执行
        // 月份设置为10，日为10， 让它基本不跑
        log.info("每五分钟运行60次的自动任务执行了");
    }

    @Scheduled(fixedDelay = 3L, timeUnit = TimeUnit.MINUTES)
    public void myScheduleTask2() {
        // 每间隔3min运行一次
        log.info("每间隔3分钟运行一次的自动任务执行了");
    }
}
