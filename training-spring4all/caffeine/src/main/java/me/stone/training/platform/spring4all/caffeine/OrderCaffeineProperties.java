package me.stone.training.platform.spring4all.caffeine;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/11 15:36
 */
@ConfigurationProperties(prefix = "me.caffeine.order")
@Getter
@Setter
public class OrderCaffeineProperties {

    private String name = "order";
    private int initSize = 10;
    private int maxSize = 1000;
    private Duration expireTime = Duration.ofMinutes(10);
}
