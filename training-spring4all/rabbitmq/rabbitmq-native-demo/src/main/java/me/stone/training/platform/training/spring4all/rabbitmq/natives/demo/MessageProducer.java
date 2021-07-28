package me.stone.training.platform.training.spring4all.rabbitmq.natives.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/27 19:45
 */
public class MessageProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        final Connection connection = Factory.newChannel();
        final Channel channel = connection.createChannel();
        final String message = "hello, rabbitmq";
        channel.basicPublish(Factory.EXCHANGE_NAME, "me.stone.best", null, message.getBytes());
        channel.close();
        connection.close();
    }

}
