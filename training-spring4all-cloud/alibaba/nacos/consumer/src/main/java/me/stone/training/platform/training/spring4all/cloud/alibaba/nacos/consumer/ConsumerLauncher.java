package me.stone.training.platform.training.spring4all.cloud.alibaba.nacos.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author honorStone
 * @Date 2021/9/13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerLauncher {


    public static void main(String[] args) {
        SpringApplication.run(ConsumerLauncher.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RestController
    static class TestController{

        @Autowired
        private RestTemplate restTemplate;

        @GetMapping(value = "/echo/{params}")
        public String echo(@PathVariable(value = "params") String params) {
            return restTemplate.getForObject("http://service-provider/echo/" + params, String.class);
        }


    }
}
