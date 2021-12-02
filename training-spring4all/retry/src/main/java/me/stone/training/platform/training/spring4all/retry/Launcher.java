package me.stone.training.platform.training.spring4all.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 10:17
 */

@SpringBootApplication
@EnableRetry
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
