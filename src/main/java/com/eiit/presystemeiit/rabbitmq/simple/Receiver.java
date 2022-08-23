package com.eiit.presystemeiit.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/16 21:23
 * @description default
 */

@Component
public class Receiver implements ChannelAwareMessageListener {

    private final Logger LOG = LogManager.getLogger();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try {
            String msg = new String(message.getBody());
            System.out.println("接收到消息 : " + msg);

            try {
                int i = 1/0;

                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                System.out.println("消息已消费");
            } catch (Exception e) {
                LOG.info("message 消费失败 : " + msg + ", 即将重发");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);                //param3 是否要求重发  false的话需要 死信队列保障消息不丢失
                throw e;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }
}
