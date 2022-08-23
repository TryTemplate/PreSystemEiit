package com.eiit.presystemeiit.config;

import com.eiit.presystemeiit.common.Constants;
import com.eiit.presystemeiit.rabbitmq.simple.Receiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

    private final Logger LOG = LogManager.getLogger();

    @Autowired
    private Receiver receiver;

    /**
     * simple模式
     * @return
     */
    @Bean
    public Queue simpleQueue(){
        return new Queue(Constants.MQ_CODE.SIMPLE_EXCHANGE_QUEUE);
    }

    /**
     * 工作队列模式
     * @return
     */
    @Bean
    public Queue workQueue(){
        return new Queue(Constants.MQ_CODE.WORK_EXCHANGE_QUEUE);
    }





    /**
     * fanout模式
     */
    //申明队列
    @Bean
    public Queue fanoutQueueOne(){
        /*
        return QueueBuilder.durable(Constants.MQ_CODE.FANOUT_EXCHANGE_QUEUE_NAME_ONE)                   //业务队列
                .withArgument("x-dead-letter-exchange", Constants.MQ_CODE.DEAD_EXCHANGE)                //死信交换机
                .withArgument("x-dead-letter-routing-key", Constants.MQ_CODE.ROUTING_DEFILD_KEY)           //此业务队列的失败路由key
                .build();
        */

        return QueueBuilder.durable(Constants.MQ_CODE.FANOUT_EXCHANGE_QUEUE_NAME_ONE).build();

    }
    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue(Constants.MQ_CODE.FANOUT_EXCHANGE_QUEUE_NAME_TWO);
    }
    //申明交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(Constants.MQ_CODE.FANOUT_EXCHANGE);
    }
    //绑定到交换机
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }





    /**
     * routing模式
     */
    //申明队列
    @Bean
    public Queue routingQueueOne(){
        return new Queue(Constants.MQ_CODE.ROUTING_EXCHANGE_QUEUE_NAME_ONE);
    }
    @Bean
    public Queue routingQueueTwo(){
        return new Queue(Constants.MQ_CODE.ROUTING_EXCHANGE_QUEUE_NAME_TWO);
    }
    //申明交换机
    @Bean
    public DirectExchange routingExchange(){
        return new DirectExchange(Constants.MQ_CODE.ROUTING_EXCHANGE);
    }
    //绑定交换机
    @Bean
    public Binding routingBindingOne(){
        return BindingBuilder.bind(routingQueueOne()).to(routingExchange()).with(Constants.MQ_CODE.ROUTING_EXCHANGE_KEY_ONE);
    }
    @Bean
    public Binding routingBindingTwo(){
        return BindingBuilder.bind(routingQueueOne()).to(routingExchange()).with(Constants.MQ_CODE.ROUTING_EXCHANGE_KEY_TWO);
    }





    /**
     * topic模式
     */
    //申明队列
    @Bean
    public Queue topicQueueNews(){
        return new Queue(Constants.MQ_CODE.TOPICS_EXCHANGE_QUEUE_NAME_NEWS);
    }
    @Bean
    public Queue topicQueueWeather(){
        return new Queue(Constants.MQ_CODE.TOPICS_EXCHANGE_QUEUE_NAME_WEATHER);
    }
    @Bean
    public Queue topicQueueMotion(){
        return new Queue(Constants.MQ_CODE.TOPICS_EXCHANGE_QUEUE_NAME_MOTION);
    }
    //申明通配符交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Constants.MQ_CODE.TOPICS_EXCHANGE);
    }
    //绑定通配符交换机与消息队列
    @Bean
    public Binding topicBindingOne(){
        return BindingBuilder.bind(topicQueueNews()).to(topicExchange()).with(Constants.MQ_CODE.TOPICS_EXCHANGE_KEY_ONE);
    }
    @Bean
    public Binding topicBindingWeather(){
        return BindingBuilder.bind(topicQueueWeather()).to(topicExchange()).with(Constants.MQ_CODE.TOPICS_EXCHANGE_KEY_TWO);
    }
    @Bean
    public Binding topicBindingMotion(){
        return BindingBuilder.bind(topicQueueMotion()).to(topicExchange()).with(Constants.MQ_CODE.TOPICS_EXCHANGE_KEY_THR);
    }


    /*

    @Bean
    public Queue deadQueueList(){
        return QueueBuilder.durable(Constants.MQ_CODE.DEAD_QUEUE_LIST)                                  //死信队列
                .withArgument("x-dead-letter-exchange", Constants.MQ_CODE.DEAD_EXCHANGE)                //死信交换机
                .withArgument("x-dead-letter-routing-key", Constants.MQ_CODE.DEAD_QUEUE_KEY)            //死信路由key
                .withArgument("x-message-ttl", Constants.MQ_CODE.DEAD_QUEUE_TTL)                      //队列过期时间
                .build();
    }

    * */

    //死信队列配置
    //死信队列
    @Bean
    public Queue deadQueueList(){
        return QueueBuilder.durable(Constants.MQ_CODE.DEAD_QUEUE_LIST).build();
    }
    //死信交换机
    @Bean
    public Exchange deadExchange(){
        return ExchangeBuilder.directExchange(Constants.MQ_CODE.DEAD_EXCHANGE).build();
    }
    //绑定
    @Bean
    public Binding delayDeadBinding(@Qualifier("deadExchange") Exchange exchange, @Qualifier("deadQueueList") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(Constants.MQ_CODE.DELAY_QUEUE_KEY).noargs();
    }
    /*
    @Bean
    public Binding routingDeadBinding(@Qualifier("deadExchange") Exchange exchange, @Qualifier("deadQueueList") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(Constants.MQ_CODE.ROUTING_DEFILD_KEY).noargs();
    }
    */



    //创建延迟队列
    @Bean
    public Queue delayQueueList(){
        return QueueBuilder.durable(Constants.MQ_CODE.DELAY_QUEUE_LIST)                                 //延迟队列
                .withArgument("x-dead-letter-exchange", Constants.MQ_CODE.DEAD_EXCHANGE)                //死信交换机
                .withArgument("x-dead-letter-routing-key", Constants.MQ_CODE.DELAY_QUEUE_KEY)           //延迟路由key
                .build();
    }









    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setConfirmCallback(confirmCallback());       //发送回调
        rabbitTemplate.setMandatory(true);                          //开路由失败的通知
        rabbitTemplate.setReturnsCallback(returnsCallback());       //消息路由失败的回调

        return rabbitTemplate;
    }


    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if (ack){
                    System.out.println("mq message sned success ! : { " + s + " }");
                }else {
                    //TODO 处理发送失败的消息
                    LOG.info("------------- mq message send defile ~ not into exchange");
                    System.out.println("please resend message count : " + s);
                }
            }
        };
    }


    @Bean
    public RabbitTemplate.ReturnsCallback returnsCallback(){
        return new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                LOG.info("消息路由失败 {}",returnedMessage.getMessage());
                LOG.error("------ 返回消息回调:{} 应答代码:{} 回复文本:{} 交换器:{} 路由键:{}", returnedMessage.getMessage(), returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getExchange(), returnedMessage.getRoutingKey());
            }
        };
    }



    //订阅模式消费者
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory factory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setQueues(fanoutQueueOne(), fanoutQueueTwo());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(receiver);

        return container;
    }


}
