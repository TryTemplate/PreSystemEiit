package com.eiit.presystemeiit.rabbitmq.simple;

import com.eiit.presystemeiit.common.Constants;
import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MhRabbitTemplate {

    private final Logger LOG = LogManager.getLogger();

    private final MessageProperties messageProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public MhRabbitTemplate(){
        this.messageProperties = new MessageProperties();
        this.messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
    }


    /**
     * 发送延迟消息
     *
     * @param msg               消息内容
     * @param second            延迟秒数
     * @param delayQueueName    发送的延迟队列名称, 向多个队列发送可传入多个参数
     * @throws AmqpException
     */
    public final void sendDelayMsg(@NotNull String msg, @NotNull Integer second, @NotNull String... delayQueueName) throws AmqpException {

        for (String dealyQueue: delayQueueName) {
            rabbitTemplate.convertAndSend(dealyQueue, msg, message -> {
                message.getMessageProperties().setExpiration(String.valueOf(second * 1000));
                return message;
            });
            LOG.info("MhRabbitTemplate sendDelayMsg : " + msg);
        }

    }













    /**
     * simple模式发送
     *
     * @param msg 消息内容
     * @param queueName 队列列表(如果给多个队列发送则多参数)
     * @throws Exception
     */
    public final void simpleMsg(@NotNull String msg, @NotNull String... queueName) throws AmqpException {

        for (String queue: queueName) {
            rabbitTemplate.send(queue, new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties));
            LOG.info("MhRabbitTemplate simpleMsg : " + msg);
        }

    }

    /**
     * 工作队列模式
     *
     * @param msg 消息内容
     * @param queueName 队列列表(如果给多个队列发送则多参数)
     * @throws Exception
     */
    public final void sendWorkMsg(@NotNull String msg, @NotNull String... queueName) throws AmqpException {

        for (String queue: queueName) {
            rabbitTemplate.send(queue, new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties));
            LOG.info("MhRabbitTemplate sendWorkMsg : " + msg);
        }

    }


    /**
     * 订阅模式发送
     *
     * @param msg 消息内容
     * @param queueName 队列列表(如果给多个队列发送则多参数)
     * @throws Exception
     */
    public final void sendFanoutMsg(@NotNull String msg, @NotNull String... queueName) throws AmqpException {

        for (String queue: queueName) {
            rabbitTemplate.send(Constants.MQ_CODE.FANOUT_EXCHANGE, queue, new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties));
            LOG.info("MhRabbitTemplate sendFanoutMsg : " + msg);
        }

    }


    /**
     * 路由模式发送
     *
     * @param msg 消息内容
     * @param routingKey 路由key列表(如果给多个队列发送则多参数)
     * @throws Exception
     */
    public final void sendRoutingtMsg(String msg, String... routingKey) throws AmqpException {
        for (String key: routingKey) {
            rabbitTemplate.convertAndSend(Constants.MQ_CODE.ROUTING_EXCHANGE, key, msg);
            LOG.info("MhRabbitTemplate sendRoutingtMsg : " + key + ", " + msg);
        }
    }


    /**
     * 通配符模式发送
     *
     * @param msg 消息内容
     * @param routingKey 路由key列表(如果给多个队列发送则多参数)
     * @throws Exception
     */
    public final void sendTopicstMsg(String msg, String... routingKey) throws AmqpException {
        for (String key: routingKey) {
            rabbitTemplate.convertAndSend(Constants.MQ_CODE.TOPICS_EXCHANGE, key, msg);
            LOG.info("MhRabbitTemplate sendTopicstMsg : " + key + ", " + msg);
        }
    }
}
