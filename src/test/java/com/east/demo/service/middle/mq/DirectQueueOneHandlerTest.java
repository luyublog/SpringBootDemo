package com.east.demo.service.middle.mq;

import com.east.demo.common.config.middle.mq.RabbitConst;
import com.east.demo.model.dto.middle.mq.MessageStruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest
class DirectQueueOneHandlerTest {
    // note:这里用Qualifier会注入失败;原因...待跟踪
    @Autowired()
    private RabbitTemplate myRabbitTemplateTest1;

    // autowired规则:首先根据类型去容器中找出所有匹配的Bean，如果只有一个就直接注入即可，
    // 如果有多个则取出来放到一个Map中（key为beanName,value为具体的bean），
    // 然后去查找Map中有@Primary的bean进行返回；如果没有，再去找Map中优先级最大的Bean进行返回；
    // 如果也没有，则根据字段名去Map中匹配key进行返回。
    // ref: https://www.cnblogs.com/fallmwu/p/14267939.html
    @Autowired()
    private RabbitTemplate myRabbitTemplateTest2;

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
        // 使用rabbitTemplate则不会有自定义回调和失败处理
//        rabbitTemplate.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
        myRabbitTemplateTest1.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
    }

    /**
     * 测试广播模式发送
     */
    @Test
    public void sendFanout() {
        myRabbitTemplateTest2.convertAndSend(RabbitConst.FANOUT_MODE_QUEUE, "", new MessageStruct("fanout message"));
    }

    /**
     * 测试主题模式发送1
     * routing-ky:key.*
     */
    @Test
    public void sendTopic1() {
        myRabbitTemplateTest1.convertAndSend(
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
        myRabbitTemplateTest1.convertAndSend(RabbitConst.TOPIC_MODE_QUEUE,
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
        myRabbitTemplateTest1.convertAndSend(
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
        myRabbitTemplateTest1.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));

    }
}