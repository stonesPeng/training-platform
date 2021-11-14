package me.stone.training.platform.training.spring4all.cloud.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honorstone
 * @description default description
 * @date 2021/11/14 22:21
 */
@SpringBootApplication
@EnableFeignClients
@RestController
public class Launcher {

    @Autowired
    private GreetingClient greetingClient;

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }

    @GetMapping("/get-greeting")
    public String greeting(){
        return greetingClient.greeting();
    }
}
