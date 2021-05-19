package me.stone.training.platform.training.spring4all.rabbitmq.demo.producer;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/18 8:59
 */
@Component
@AllArgsConstructor
public class CustomizeProducer {

    private final AmqpTemplate amqpTemplate;

    public void invoke() {
        amqpTemplate.convertAndSend("", "FIRST_QUEUE", "-------- a direct msg");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "me.stone.training", "-------- a topic msg : me.stone.training");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "me.stone.training.spring4all", "-------- a topic msg : me.stone.training.spring4all");
        amqpTemplate.convertAndSend("FANOUT_EXCHANGE", "", "-------- a fanout msg");
    }
}
