package com.east.demo.service.middle.kafka;

import com.east.demo.common.config.middle.kafka.KafkaConst;
import com.east.demo.common.exception.BaseException;
import com.east.demo.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * kafka消息处理器
 *
 * 参考：
 * <a href="https://cloud.tencent.com/developer/article/1542310">实战：彻底搞定 SpringBoot 整合 Kafka（spring-kafka深入探秘）</a>
 *
 * @author: east
 * @date: 2023/10/29
 */

@Slf4j
@Service
public class KafkaMessageHandler {

    /**
     * 测试kafka如果不ack，该消费者会不会卡住。不会卡住。如果后续消费ack了那么这条就会跳过, offset就会拉到最新
     *
     * @param record record
     * @param acknowledgment ack
     */
    @KafkaListener(topics = KafkaConst.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            String key = record.key();
            String message = record.value();

            // 异常测试
            if ("error flag".equals(message)) {
                throw new BaseException(ErrorEnum.FAIL);
            }

            log.info("消息接收成功：[{}] \t topic: [{}] \t partition: [{}] \t leader epoch: [{}] \t key: [{}] \t offset: [{}]",
                    message, record.topic(), record.partition(), record.leaderEpoch().get(),
                    key, record.offset());
            log.info(record.headers().toArray().toString());
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.info("消息接收失败：[{}] \t topic: [{}] \t partition: [{}] \t leader epoch: [{}] \t key: [{}] \t offset: [{}]",
                    record.value(), record.topic(), record.partition(), record.leaderEpoch().get(),
                    record.key(), record.offset());
            log.error(e.getMessage(), e);
        }
    }

    @KafkaListener(id = KafkaConst.GROUP_ID, topicPartitions = {
            @TopicPartition(topic = KafkaConst.TOPIC_TEST, partitions = {"4"}),
            @TopicPartition(topic = KafkaConst.TOPIC_TEST_2, partitions = {"0", "1"})
    }, concurrency = "3", errorHandler = "myErrorHandler")
    public void specifyHandleMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            String key = record.key();
            String message = record.value();

            log.info("消息接收成功：[{}] \t topic: [{}] \t partition: [{}] \t leader epoch: [{}] \t key: [{}] \t offset: [{}]",
                    message, record.topic(), record.partition(), record.leaderEpoch().get(),
                    key, record.offset());
            log.info(record.headers().toArray().toString());

            // test occur a exception
            if (true) {
                throw new BaseException(ErrorEnum.FAIL);
            }

            // 手动提交 offset
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Bean不能放在这里，原因：
     * 1. 循环依赖问题
     * 2. 配置与业务逻辑混乱
     * 3.
     */
//    @Bean(name = "myErrorHandler")
//    public KafkaListenerErrorHandler myErrorHandler(){
//        return new KafkaListenerErrorHandler() {
//            @Override
//            public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
//                log.info("接收失败 message: {}",message.getPayload());
//                log.error("异常：",exception);
//                return null;
//            }
//
//            /**
//             * Handle the error.
//             *
//             * @param message   the spring-messaging message.
//             * @param exception the exception the listener threw, wrapped in a
//             *                  {@link ListenerExecutionFailedException}.
//             * @param consumer  the consumer.
//             * @return the return value is ignored unless the annotated method has a
//             * {@code @SendTo} annotation.
//             */
//            @Override
//            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
//                log.info("接收失败 message: {}",message.getPayload());
//                log.error("异常：",exception);
//                return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
//            }
//        };
//    }
}
