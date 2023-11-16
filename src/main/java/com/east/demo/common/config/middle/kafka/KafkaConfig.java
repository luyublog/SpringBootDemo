package com.east.demo.common.config.middle.kafka;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

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

}
