package me.stone.training.platform.spring4all.caffeine.autoconfigure;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import me.stone.training.platform.spring4all.caffeine.OrderCaffeineProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/11 15:37
 */
@EnableConfigurationProperties(OrderCaffeineProperties.class)
@Configuration
public class OrderCaffeineAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(
        name = {"orderCache"}
    )
    public Cache<Long, BigDecimal> orderCache(OrderCaffeineProperties properties) {
        return Caffeine.newBuilder()
            .initialCapacity(properties.getInitSize())
            .maximumSize(properties.getMaxSize())
            .expireAfterWrite(properties.getExpireTime().getSeconds(), TimeUnit.SECONDS)
            .build();
    }
}
