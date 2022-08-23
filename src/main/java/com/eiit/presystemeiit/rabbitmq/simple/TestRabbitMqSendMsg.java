package com.eiit.presystemeiit.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestRabbitMqSendMsg {
    final static String QUEUE_NAME = "service_technical";

    public static void main(String[] args) throws Exception {
        ConnectionFactory mqFactory = new ConnectionFactory();
        mqFactory.setHost("mq.tcztk.com");
        mqFactory.setPort(5672);
        mqFactory.setVirtualHost("/presystemeiit");
        mqFactory.setUsername("root");
        mqFactory.setPassword("ljg123..");

        Connection connection = mqFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String msg = "this is a task";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("send success, content: " + msg);
        channel.close();
        connection.close();

    }
}
