package me.stone.training.platform.training.spring4all.rabbitmq.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/18 9:05
 */
@Component
@Configuration
public class CustomizeConsumers {


    @Component
    @RabbitListener(queues = "ZERO_QUEUE")
    public static class FirstConsumer {
        @RabbitHandler
        public void process(String msg) {
            System.out.println(" zero queue received msg : " + msg);
        }
    }

    @Component
    @RabbitListener(queues = "FIRST_QUEUE")
    public static class SecondConsumer {
        @RabbitHandler
        public void process(String msg) {
            System.out.println(" first queue received msg : " + msg);
        }
    }

    @Component
    @RabbitListener(queues = "SECOND_QUEUE")
    public static class ThirdConsumer {
        @RabbitHandler
        public void process(String msg) {
            System.out.println(" second queue received msg : " + msg);
        }
    }

}
