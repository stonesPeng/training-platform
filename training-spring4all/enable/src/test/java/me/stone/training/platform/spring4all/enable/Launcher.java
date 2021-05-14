package me.stone.training.platform.spring4all.enable;

import me.stone.training.platform.spring4all.enable.configure.BuzAutoConfigure;
import me.stone.training.platform.spring4all.enable.configure.EnableRealInvoke;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 14:20
 */
@SpringBootApplication
@EnableRealInvoke
@Import({
    BuzAutoConfigure.class
})
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
