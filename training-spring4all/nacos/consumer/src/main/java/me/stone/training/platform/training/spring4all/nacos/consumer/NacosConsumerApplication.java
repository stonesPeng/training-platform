package me.stone.training.platform.training.spring4all.nacos.consumer;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @Author honorStone
 * @Date 2021/9/9
 */
@SpringBootApplication
public class NacosConsumerApplication{

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class);
    }


    @NacosInjected
    private NamingService namingService;


    @SneakyThrows
    @PostConstruct
    public void doConsume(){
        namingService.selectOneHealthyInstance("nacos.discovery.client");
        final RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://ip:45451/discovery/get?serviceName=nacos.discovery.client", "121.89.226.47", 45451);
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(String.format("请求URL:%s,响应结果:%s", url, result));
    }
}
