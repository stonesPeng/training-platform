package me.stone.training.platform.training.spring4all.nacos.discovery.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author honorStone
 * @Date 2021/9/7
 */
@RestController
@RequestMapping("/discovery")
@Slf4j
public class NacosDiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

    @PostConstruct
    public void registerInstance() throws NacosException {
        log.debug("serverPort:{}",serverPort);
        namingService.registerInstance(applicationName,"ip",serverPort);
    }

    @SneakyThrows
    @GetMapping(value = "/get")
    public List<Instance> get(@RequestParam String serviceName){
        log.debug("Nacos Discovery into get method...{}",serviceName);
        return namingService.getAllInstances(serviceName);
    }

}
