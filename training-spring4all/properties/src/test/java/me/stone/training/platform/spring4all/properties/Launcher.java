package me.stone.training.platform.spring4all.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 16:04
 */
@SpringBootApplication
@Slf4j
public class Launcher {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Launcher.class, args);
        final BuzProperties bean1 = ctx.getBean(BuzProperties.class);
        final BuzProperties.BuzSubProperties bean2 = ctx.getBean(BuzProperties.BuzSubProperties.class);
        log.info(bean1.toString());
        log.info(bean2.toString());
        ctx.close();
    }
}
