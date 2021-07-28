package me.stone.training.platform.training.spring4all.rabbitmq.demo.producer;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/18 8:59
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
@Component
@AllArgsConstructor
public class CustomizeProducer {

    private final AmqpTemplate amqpTemplate;

    public void invoke() {
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 100000; i++) {
            executorService.submit(() -> {
                amqpTemplate.convertAndSend("", "FIRST_QUEUE", "-------- a direct msg");
                Thread.yield();
                amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "me.stone.training", "-------- a topic msg : me.stone.training");
                Thread.yield();
                amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "me.stone.training.spring4all", "-------- a topic msg : me.stone.training.spring4all");
                amqpTemplate.convertAndSend("FANOUT_EXCHANGE", "", "-------- a fanout msg");

            });
        }
        executorService.shutdown();
    }
}
