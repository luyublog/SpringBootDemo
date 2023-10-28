package com.east.demo.common.config.midlle.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>
 * RabbitMQ配置，主要是配置队列，如果提前存在该队列，可以省略本配置类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-29 17:03
 */
@Slf4j
@Configuration
@ConditionalOnProperty("spring.rabbitmq.enable")
public class RabbitMqConfig extends RabbitAutoConfiguration {
    // 自动配置的template
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void modifyRabbitTemplate() {
        // 设置一些参数.不能放构造函数内，不然rabbitTemplate也没初始化
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause)
                -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)
                -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
    }

    /**
     * 广播模式交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConst.FANOUT_MODE_QUEUE);
    }

    /**
     * 主题模式交换机
     * <li>路由格式必须以 . 分隔，比如 user.email 或者 user.aaa.email</li>
     * <li>通配符 * ，代表一个占位符，或者说一个单词，比如路由为 user.*，那么 user.email 可以匹配，但是 user.aaa.email 就匹配不了</li>
     * <li>通配符 # ，代表一个或多个占位符，或者说一个或多个单词，比如路由为 user.#，那么 user.email 可以匹配，user.aaa.email 也可以匹配</li>
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConst.TOPIC_MODE_QUEUE);
    }

    /**
     * 直接模式队列1
     */
    @Bean
    public Queue directOneQueue() {
        return new Queue(RabbitConst.DIRECT_MODE_QUEUE_ONE);
    }

    /**
     * 队列2
     */
    @Bean
    public Queue queueTwo() {
        return new Queue(RabbitConst.QUEUE_TWO);
    }

    /**
     * 队列3
     */
    @Bean
    public Queue queueThree() {
        return new Queue(RabbitConst.QUEUE_THREE);
    }



    /**
     * 广播模式绑定队列1
     *
     *
     * @param directOneQueue 绑定队列1
     * @param fanoutExchange 广播模式交换器
     */
    @Bean
    public Binding fanoutBinding1(Queue directOneQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(directOneQueue).to(fanoutExchange);
    }

    /**
     * 广播模式绑定队列2
     *
     * @param queueTwo       绑定队列2
     * @param fanoutExchange 广播模式交换器
     */
    @Bean
    public Binding fanoutBinding2(Queue queueTwo, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueTwo).to(fanoutExchange);
    }




    /**
     * 主题模式绑定广播模式
     * key:key.#
     *
     * @param fanoutExchange 广播模式交换器
     * @param topicExchange  主题模式交换器
     */
    @Bean
    public Binding topicBinding1(FanoutExchange fanoutExchange, TopicExchange topicExchange) {
        return BindingBuilder.bind(fanoutExchange).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_SUFFIX_MORE);
    }

    /**
     * 主题模式绑定队列2
     *
     * key: *.key
     *
     * @param queueTwo      队列2
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding2(Queue queueTwo, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueTwo).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_PREFIX);
    }

    /**
     * 主题模式绑定队列3
     * key: 3.key
     *
     * @param queueThree    队列3
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding3(Queue queueThree, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueThree).to(topicExchange).with(RabbitConst.TOPIC_ROUTING_KEY_THREE);
    }

//    /**
//     * 延迟队列
//     */
//    @Bean
//    public Queue delayQueue() {
//        return new Queue(RabbitConst.DELAY_QUEUE, true);
//    }
//
//    /**
//     * 延迟队列交换器, x-delayed-type 和 x-delayed-message 固定
//     */
//    @Bean
//    public CustomExchange delayExchange() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        return new CustomExchange(RabbitConst.DELAY_MODE_QUEUE, "x-delayed-message", true, false, args);
//    }
//
//    /**
//     * 延迟队列绑定自定义交换器
//     *
//     * @param delayQueue    队列
//     * @param delayExchange 延迟交换器
//     */
//    @Bean
//    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
//        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitConst.DELAY_QUEUE).noargs();
//    }

}
