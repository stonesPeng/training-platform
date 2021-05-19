package me.stone.training.platform.training.spring4all.rabbitmq.demo;

import me.stone.training.platform.training.spring4all.rabbitmq.demo.producer.CustomizeProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/18 9:02
 */
@SpringBootApplication

public class Launcher {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Launcher.class, args);
        final CustomizeProducer bean = ctx.getBean(CustomizeProducer.class);
        bean.invoke();
        ctx.close();
    }
}
