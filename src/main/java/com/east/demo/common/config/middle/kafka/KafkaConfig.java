package com.east.demo.common.config.middle.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

/**
 * kafka配置
 *
 * @author: east
 * @date: 2023/10/29
 */


// 配置类
@Configuration
// 启用KafkaProperties类配置属性
@EnableConfigurationProperties({KafkaProperties.class})
// 手动开启配置Kafka（这里是因为在自动配置里排除了）
@EnableKafka
// 构造函数，为了初始化：kafkaProperties
@AllArgsConstructor
// 配置条件启用
@ConditionalOnProperty("spring.kafka.enable")
@Slf4j
public class KafkaConfig {
    private final KafkaProperties kafkaProperties;

    /**
     * 根据配置生成kafka生产者工厂
     *
     * @return kafka生产者工厂
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    /**
     * 根据工厂实例生成kafka生产者模板实例
     *
     * @return kafka生产者实例
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    /**
     * 创建Kafka消费者。
     * @return Kafka消费者。
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }


    /**
     * kafka监听容器listener配置
     * listener he consumer区别： consumer作为kafka客户端去poll，然后调用listener线程
     * https://blog.csdn.net/dshf_1/article/details/103203920
     * @return kafka监听者
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // 线程数：3 已经在yml配置过了
//        factory.setConcurrency(KafkaConst.DEFAULT_PARTITION_NUM);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }


    /**
     * 这里特殊配置一个特别的kafka监听容器listener配置
     *
     * https://blog.csdn.net/dshf_1/article/details/103203920
     * @return kafka监听者
     */
    @Bean("ackContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConcurrency(KafkaConst.DEFAULT_CONCURRENCY_NUM);
        return factory;
    }

    /**
     * 自定义消费者出现异常处理情况
     *
     * @return handler
     */
    @Bean(name = "myErrorHandler")
    public KafkaListenerErrorHandler myErrorHandler() {
        return new KafkaListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
                log.info("接收失败 message: {}", message.getPayload());
                log.error("异常：", exception);
                return null;
            }

            /**
             * Handle the error.
             *
             * @param message   the spring-messaging message.
             * @param exception the exception the listener threw, wrapped in a
             *                  {@link ListenerExecutionFailedException}.
             * @param consumer  the consumer.
             * @return the return value is ignored unless the annotated method has a
             * {@code @SendTo} annotation.
             */
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                log.info("接收失败 message: {}", message.getPayload());
                log.error("异常：", exception);
                return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
            }
        };
    }

}
