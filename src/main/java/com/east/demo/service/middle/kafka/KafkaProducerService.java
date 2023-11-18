package com.east.demo.service.middle.kafka;

import com.east.demo.common.config.middle.kafka.KafkaConst;
import com.east.demo.common.exception.BaseException;
import com.east.demo.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Kafka生产者
 *
 * @author: east
 * @date: 2023/11/18
 */

@Service
@Slf4j
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String key, Integer partition) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(KafkaConst.TOPIC_TEST, partition, key, message);

        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                // 处理发送成功的情况
                log.info("消息发送成功! message：[{}] \t topic: [{}] \t partition: [{}] \t key: [{}] \t offset: [{}]",
                        message, result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
                        key, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                // 处理发送失败的情况
                log.info("消息发送失败：[{}] \t topic: [{}]",
                        message, KafkaConst.TOPIC_TEST);
                throw new BaseException(ErrorEnum.FAIL, () -> String.format("kafka消息发送失败：%s", message), ex);
            }
        });
    }
}
