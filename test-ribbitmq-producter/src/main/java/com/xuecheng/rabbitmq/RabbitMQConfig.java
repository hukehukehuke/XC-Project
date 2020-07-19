package com.xuecheng.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_EMAIL = "email queue";
    public static final String QUEUE_SMS = "sms queue";
    public static final String EXCHANGE_ROUTING = "exchage routing";
    public static final String ROUTINGKEY_ERROR_EMAIL = "routingKey error";
    public static final String ROUTINGKEY_FALL_SMS = "routingKey fall";

    //声明交换机
    @Bean(value = EXCHANGE_ROUTING)
    public Exchange EXCHANGE_ROUTING(){
        return ExchangeBuilder.topicExchange(EXCHANGE_ROUTING).durable(true).build();
    }

    //声明Email队列
    @Bean(value = "QUEUE_EMAIL")
    public Queue ROUTINGKEY_ERROR_EMAIL(){
        return new Queue(ROUTINGKEY_ERROR_EMAIL);
    }

    //声明短信队列
    @Bean(value = "QUEUE_SMS")
    public Queue ROUTINGKEY_FALL_SMS(){
        return new Queue(ROUTINGKEY_FALL_SMS);
    }

    //队列绑定交换机 指定routingKey
    @Bean
    public Binding ROUTINGKEY_ERROR_EMAIL(@Qualifier(value = "QUEUE_EMAIL") Queue queue,
                                          @Qualifier(value = "EXCHANGE_ROUTING") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(EXCHANGE_ROUTING).noargs();
    }

    @Bean
    public Binding ROUTINGKEY_FALL_SMS(@Qualifier(value = "QUEUE_SMS") Queue queue,
                                          @Qualifier(value = "EXCHANGE_ROUTING") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(EXCHANGE_ROUTING).noargs();
    }

}
