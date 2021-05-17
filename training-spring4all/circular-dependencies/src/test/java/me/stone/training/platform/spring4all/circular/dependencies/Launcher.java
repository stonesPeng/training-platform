package me.stone.training.platform.spring4all.circular.dependencies;

import me.stone.training.platform.spring4all.circular.dependencies.object.case3.TestBeanA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 17:17
 */
@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Launcher.class, args);
        final TestBeanA bean = ctx.getBean(TestBeanA.class);
        bean.doSomething();
        ctx.close();
    }
}
