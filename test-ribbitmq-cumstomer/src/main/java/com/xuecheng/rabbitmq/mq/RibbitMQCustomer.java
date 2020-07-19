package com.xuecheng.rabbitmq.mq;

import com.xuecheng.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import java.nio.channels.Channel;

@Service
public class RibbitMQCustomer {

    @RabbitListener(queues = {RabbitMQConfig.EXCHANGE_ROUTING})
    public void send_message(String msg, Message message, Channel channel){

    }
}
