package me.stone.training.platform.training.spring4all.cloud.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author honorstone
 * @description default description
 * @date 2021/11/14 22:20
 */
@FeignClient("spring-cloud-eureka-client")
public interface GreetingClient {
    
    @GetMapping("/greeting")
    String greeting();
}
