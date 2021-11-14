package me.stone.training.platform.training.spring4all.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author honorstone
 * @description default description
 * @date 2021/11/14 17:06
 */
@SpringBootApplication
@EnableEurekaServer
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }
}
