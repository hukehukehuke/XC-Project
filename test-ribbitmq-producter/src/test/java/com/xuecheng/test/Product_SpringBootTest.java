package com.xuecheng.test;

import com.xuecheng.rabbitmq.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Product_SpringBootTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public  void  testEmail(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_ROUTING,"","");
    }
}
