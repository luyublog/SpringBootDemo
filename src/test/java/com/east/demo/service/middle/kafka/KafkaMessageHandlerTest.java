package com.east.demo.service.middle.kafka;

import com.east.demo.common.config.middle.kafka.KafkaConst;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;


/**
 * kafka相关
 * <p>
 * kafka配置解释：https://www.conduktor.io/kafka/kafka-topics-choosing-the-replication-factor-and-partitions-count/
 */
@SpringBootTest
class KafkaMessageHandlerTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 测试发送消息
     */
    @Test
    public void testSend() {
        kafkaTemplate.send(KafkaConst.TOPIC_TEST, "hello,kafka...");
    }

}