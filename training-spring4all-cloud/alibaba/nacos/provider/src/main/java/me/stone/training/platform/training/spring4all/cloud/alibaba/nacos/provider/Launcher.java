package me.stone.training.platform.training.spring4all.cloud.alibaba.nacos.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author honorStone
 * @Date 2021/9/13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class);
    }

    @Value("${server.port}")
    private Integer servicePort;

    @RestController
    class EchoController{

        @GetMapping(value = "/echo/{params}")
        public String echo(@PathVariable(value = "params") String params){
            System.out.println("i am service provider,now "+servicePort);
            return "Hello Nacos Discovery " + params;
        }
    }
}
