package com.xuecheng.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RibbitMQCustomer_Email {
    private static final String QUEUE_EMAIL = "email queue";
    private static final String QUEUE_SMS = "sms queue";
    private static final String EXCHAGE = "exchage";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //设置虚拟机 每个MQ可以设置多个虚拟机
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * queue, durable, exclusive,  autoDelete,  arguments
         *  1、queue 队列名称
         *  2、durable 是否持久化 如果持久化，mq重启后队列还在
         *  3、exclusive 是否独占连接 ，如果Connection关闭则自动删除，如果将此参数设置为true可用于临时队列的创建
         *  4、autoDelete  自动删除 队列不再使用就自动删除
         *  5、arguments 此参数可以设置队列的扩展参数
         */
        channel.queueDeclare(QUEUE_EMAIL, true, false, false, null);
        //声明交换机
        /**
         * exchange : 交换机名称
         * type :  交换机类型
         * fanout :  publish/subscribe
         * direct : 路由工作模式
         * topic :通配符工作模式
         * header : header模式
         * rpc ：远程调用
         */
        channel.exchangeDeclare(EXCHAGE, BuiltinExchangeType.FANOUT);
        //交换机和队列进行绑定
        /**
         * queue :队列名称
         * exchange ： 交换机名称
         * rutingKey : 路由key在发布订阅模式中 将key设置为null
         *
         */
        channel.exchangeBind(QUEUE_EMAIL,EXCHAGE,"");
        //实现消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //当接收到消息之后此方法将被调用

            /**
             * @param consumerTag 用来标识消费者  在监听消费者的时候设置   channel.basicConsume()
             * @param envelope    消息信封
             * @param properties  消息属性
             * @param body        消息内容
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获取交换机
                String exchange = envelope.getExchange();
                //消费ID  mq在channel通道中标志消息的id  可以用来确认消息是否发送
                long deliveryTag = envelope.getDeliveryTag();
                //消息内容
                String message = new String(body, "UTF-8");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_EMAIL,true,defaultConsumer);
    }
}


