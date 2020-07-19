package com.xuecheng.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ProductTest {

    private static final String QUEUE = "hello world";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //设置虚拟机 每个MQ可以设置多个虚拟机
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            /**
             * queue, durable, exclusive,  autoDelete,  arguments
             *  1、queue 队列名称
             *  2、durable 是否持久化 如果持久化，mq重启后队列还在
             *  3、exclusive 是否独占连接 ，如果Connection关闭则自动删除，如果将此参数设置为true可用于临时队列的创建
             *  4、autoDelete  自动删除 队列不再使用就自动删除
             *  5、arguments 此参数可以设置队列的扩展参数
             */
            channel.queueDeclare(QUEUE, true, false, false, null);
            /**
             * exchange, routingKey,  mandatory,immediate, props,  body
             * 1、exchange 交换机 如果不设置将使用mq的默认交换机
             * 2、routingKey  路由key 交换机根据路由key分发消息到指定队列
             * 3、props 消息属性
             * 4、body 消息内容
             */
            channel.basicPublish(" ", QUEUE, null, null);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
