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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void directHandlerManualAck() {
        rabbitTemplate.convertAndSend(RabbitConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
    }
}