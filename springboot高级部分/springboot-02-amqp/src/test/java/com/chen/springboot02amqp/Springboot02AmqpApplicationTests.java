package com.chen.springboot02amqp;

import com.chen.springboot02amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂ConnectionFactory；
 *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
 *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 *
 */

@SpringBootTest
class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){

        // 看实现类
		// amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		// System.out.println("创建完成");

		// amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        // 创建绑定规则 目的地，目的地类型（消息队列/交换器），路由键，参数头
		amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

        // 删除操作
        // amqpAdmin.de
    }

    /**
     * 1.单播（点对点）
     */

    @Test
    void contextLoads() {

        // Message需要自己构造一个;定义消息体内容和消息头
        // rabbitTemplate.send(exchage,routeKey,message);

        // object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        // rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange:direct","atguigu.news",map);
    }
    //接受数据,如何将数据自动的转为json发送出去
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange:fanout","",new Book("七龙珠","不知道"));
    }

}
