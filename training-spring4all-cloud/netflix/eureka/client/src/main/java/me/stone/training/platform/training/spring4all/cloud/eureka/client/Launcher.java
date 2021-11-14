package me.stone.training.platform.training.spring4all.cloud.eureka.client;

import com.netflix.discovery.EurekaClient;
import me.stone.training.platform.training.spring4all.cloud.eureka.client.controller.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honorstone
 * @description default description
 * @date 2021/11/14 17:20
 */
@SpringBootApplication
@RestController
public class Launcher implements GreetingController {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String greeting() {
        return String.format(
            "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }
}
