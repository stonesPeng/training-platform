package me.stone.training.platform.training.spring4all.rabbitmq.natives.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/27 19:58
 */
public class MessageConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        final Connection connection = Factory.newChannel();
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(Factory.EXCHANGE_NAME, "direct", false, false, null);
        channel.queueDeclare(Factory.QUEUE_NAME, false, false, false, null);

        System.out.println("waiting for message ....");

        channel.queueBind(Factory.QUEUE_NAME, Factory.EXCHANGE_NAME, "me.stone.best");

        final DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                final String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("received message: " + message + "\n");
                System.out.println("consumer tag: " + consumerTag + "\n");
                System.out.println("delivery tag: " + envelope.getDeliveryTag() + "\n");
            }
        };

        channel.basicConsume(Factory.QUEUE_NAME, true, defaultConsumer);
        channel.close();
        connection.close();
    }
}
