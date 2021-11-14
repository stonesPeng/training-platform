package me.stone.training.platform.training.spring4all.cloud.eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author honorstone
 * @description default description
 * @date 2021/11/14 17:21
 */
public interface GreetingController {

    @GetMapping("/greeting")
    String greeting();
}
