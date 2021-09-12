package me.stone.training.platform.training.spring4all.nacos.discovery.autoconfiguration;

import me.stone.training.platform.training.spring4all.nacos.discovery.controller.NacosDiscoveryController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author honorStone
 * @Date 2021/9/7
 */

@Configuration
@Import({
    NacosDiscoveryController.class
})
public class NacosDiscoveryAutoConfiguration {


}
