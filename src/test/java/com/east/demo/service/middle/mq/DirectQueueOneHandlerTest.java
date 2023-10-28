package com.east.demo.service.middle.mq;

import com.east.demo.common.config.midlle.mq.RabbitConst;
import com.east.demo.dto.middle.mq.MessageStruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
class DirectQueueOneHandlerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test down");
    }

    @Test
    void directHandlerManualAck() {
        rabbitTemplate.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
    }

    /**
     * 测试广播模式发送
     */
    @Test
    public void sendFanout() {
        rabbitTemplate.convertAndSend(RabbitConst.FANOUT_MODE_QUEUE, "", new MessageStruct("fanout message"));
    }

    /**
     * 测试主题模式发送1
     * routing-ky:key.*
     */
    @Test
    public void sendTopic1() {
        rabbitTemplate.convertAndSend(
                RabbitConst.TOPIC_MODE_QUEUE,
                "key.aaa.bbb",
                new MessageStruct("topic message with key.aaa.bbb"));
    }

    /**
     * 测试主题模式-发送2
     * routing-key: *.key
     */
    @Test
    public void sendTopic2() {
        rabbitTemplate.convertAndSend(RabbitConst.TOPIC_MODE_QUEUE,
                "ccc.key",
                new MessageStruct("topic message with ccc.key"));
    }

    /**
     * 测试主题模式发送3
     * <p>
     * routing-key: 3.key
     */
    @Test
    public void sendTopic3() {
        rabbitTemplate.convertAndSend(
                RabbitConst.TOPIC_MODE_QUEUE,
                "3.queue",
                new MessageStruct("topic message with 3.key"));
    }

    /**
     * 测试超时，ack失败情况
     * <p>
     * routing-key: 3.key
     */
    @Test
    void testTimeout() throws InterruptedException {
        // 放在这里测试生产者
//        Thread.sleep(10*1000);
        rabbitTemplate.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));

    }
}