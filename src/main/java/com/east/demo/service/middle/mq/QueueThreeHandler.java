package com.east.demo.service.middle.mq;

import cn.hutool.json.JSONUtil;
import com.east.demo.common.config.middle.mq.RabbitConst;
import com.east.demo.model.dto.middle.mq.MessageStruct;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 队列2处理逻辑
 *
 * @author: east
 * @date: 2023/10/28
 */

@Slf4j
@RabbitListener(queues = RabbitConst.QUEUE_THREE)
@Component
public class QueueThreeHandler {
    @RabbitHandler
    public void directHandlerManualAck(MessageStruct messageStruct, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("队列3，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("接收失败，数据回滚");
            try {
                log.info("处理失败,让结点重发一遍");
                channel.basicRecover();
            } catch (IOException e1) {
                log.error("二次处理还是失败，数据回滚", e1);
                e1.printStackTrace();
            }
        }
    }
}
