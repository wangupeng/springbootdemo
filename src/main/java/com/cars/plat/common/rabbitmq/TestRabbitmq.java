package com.cars.plat.common.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 1、一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中
 * 2、N个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中
 * 高级使用
 * 对象的支持
 *
 * springboot以及完美的支持对象的发送和接收，不需要格外的配置。
 * //发送者
 * public void send(User user) {
 *     System.out.println("Sender object: " + user.toString());
 *     this.rabbitTemplate.convertAndSend("object", user);
 * }
 * //接受者
 * @RabbitHandler
 * public void process(User user) {
 *     System.out.println("Receiver object : " + user);
 * }
 * 结果如下：
 * Sender object: User{name='neo', pass='123456'}
 * Receiver object : User{name='neo', pass='123456'}
 * Topic Exchange
 *
 * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
 * 首先对topic规则配置，这里使用两个队列来测试
 * @Configuration
 * public class TopicRabbitConfig {
 *     final static String message = "topic.message";
 *     final static String messages = "topic.messages";
 *     @Bean
 *     public Queue queueMessage() {
 *         return new Queue(TopicRabbitConfig.message);
 *     }
 *     @Bean
 *     public Queue queueMessages() {
 *         return new Queue(TopicRabbitConfig.messages);
 *     }
 *     @Bean
 *     TopicExchange exchange() {
 *         return new TopicExchange("exchange");
 *     }
 *     @Bean
 *     Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
 *         return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
 *     }
 *     @Bean
 *     Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
 *         return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
 *     }
 * }
 * 使用queueMessages同时匹配两个队列，queueMessage只匹配"topic.message"队列
 *
 * public void send1() {
 *     String context = "hi, i am message 1";
 *     System.out.println("Sender : " + context);
 *     this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
 * }
 *
 * public void send2() {
 *     String context = "hi, i am messages 2";
 *     System.out.println("Sender : " + context);
 *     this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
 * }
 * 发送send1会匹配到topic.#和topic.message 两个Receiver都可以收到消息，发送send2只有topic.#可以匹配所有只有Receiver2监听到消息
 *
 * Fanout Exchange
 *
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
 *
 * Fanout 相关配置
 *
 * @Configuration
 * public class FanoutRabbitConfig {
 *     @Bean
 *     public Queue AMessage() {return new Queue("fanout.A");}
 *     @Bean
 *     public Queue BMessage() { return new Queue("fanout.B");}
 *     @Bean
 *     public Queue CMessage() { return new Queue("fanout.C"); }
 *     @Bean
 *     FanoutExchange fanoutExchange() { return new FanoutExchange("fanoutExchange"); }
 *     @Bean
 *     Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
 *         return BindingBuilder.bind(AMessage).to(fanoutExchange);
 *     }
 *     @Bean
 *     Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
 *         return BindingBuilder.bind(BMessage).to(fanoutExchange);
 *     }
 *     @Bean
 *     Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
 *         return BindingBuilder.bind(CMessage).to(fanoutExchange);
 *     }
 * }
 * 这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：
 *
 * public void send() {
 *         String context = "hi, fanout msg ";
 *         System.out.println("Sender : " + context);
 *         this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
 * }
 * 结果如下：
 *
 * Sender : hi, fanout msg
 * ...
 * fanout Receiver B: hi, fanout msg
 * fanout Receiver A  : hi, fanout msg
 * fanout Receiver C: hi, fanout msg
 * 结果说明，绑定到fanout交换机上面的队列都收到了消息
 * Created by wangyupeng on 2018/4/12 17:37
 */
@Component
public class TestRabbitmq {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Scheduled(fixedDelay=3000)//3s执行1次此方法;
    public void testSend() {
        System.out.println("发送消息");
        rabbitTemplate.convertAndSend("test_mq", "SpringBoot发送消息");
    }
}
