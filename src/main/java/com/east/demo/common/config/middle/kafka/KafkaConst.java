package com.east.demo.common.config.middle.kafka;

/**
 * kafka常量
 *
 * @author: east
 * @date: 2023/10/29
 */

public class KafkaConst {
    /**
     * 默认并发数
     */
    public static final Integer DEFAULT_CONCURRENCY_NUM = 3;

    /**
     * 第1个Topic名称
     */
    public static final String TOPIC_TEST = "TOPIC_LY_TEST";

    /**
     * 第2个Topic名称
     */
    public static final String TOPIC_TEST_2 = "messages";

    /**
     * Topic 名称
     */
    public static final String GROUP_ID = "spring-boot-demo-group";
}
