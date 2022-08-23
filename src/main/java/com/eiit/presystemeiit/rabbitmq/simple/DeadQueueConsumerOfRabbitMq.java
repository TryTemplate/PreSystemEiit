package com.eiit.presystemeiit.rabbitmq.simple;

import com.eiit.presystemeiit.common.Constants;
import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/18 20:42
 * @description mq消费者
 */

@Component
public class DeadQueueConsumerOfRabbitMq {

    private final Logger log = LogManager.getLogger();

    @RabbitListener(queues = Constants.MQ_CODE.DEAD_QUEUE_LIST)
    public void receive(String msg){
        log.info("consumerDeadMq receive dead queue list mesg : " + msg);
    }

//    @RabbitListener(queues = Constants.MQ_CODE.FANOUT_EXCHANGE_QUEUE_NAME_TWO)
    public void fanoutQueueTwo(String msg, Channel channel){
        log.info("consumerDeadMq fanoutQueueTwo fanout_queue_two mesg : " + msg);
    }


}
