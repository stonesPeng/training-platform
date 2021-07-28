package me.stone.training.platform.training.spring4all.rabbitmq.natives.demo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/27 19:59
 */
public interface Factory {

    String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    String QUEUE_NAME = "SIMPLE_QUEUE";

    static Connection newChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.107");
        factory.setPort(5672);
        // 设置虚拟机
        factory.setVirtualHost("/");
        //设置用户密码
        factory.setUsername("guest");
        factory.setPassword("guest");
        //建立连接
        return factory.newConnection();
    }


}
