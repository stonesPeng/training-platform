package me.stone.training.platform.training.spring4all.cloud.alibaba.nacos.config.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author honorStone
 * @Date 2021/9/13
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Value("${log.time:10}")
    private String time;

    @GetMapping("/get")
    public boolean get(){
        return useLocalCache;
    }

    @GetMapping("/get/string")
    public String getString(){
        return time;
    }
}
