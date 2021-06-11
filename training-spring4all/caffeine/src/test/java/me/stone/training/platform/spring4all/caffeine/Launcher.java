package me.stone.training.platform.spring4all.caffeine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/11 15:43
 */

@SpringBootApplication
@Slf4j
public class Launcher {

    @SneakyThrows
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(Launcher.class, args);
        final Cache<Long, BigDecimal> orderCache = (Cache<Long, BigDecimal>) ctx.getBean("orderCache", new TypeReference<Cache<Long, BigDecimal>>() {
        });
        orderCache.put(1L, BigDecimal.valueOf(100));
        orderCache.put(2L, BigDecimal.valueOf(200));
        log.info("start...");
        orderCache.asMap().forEach((k, v) -> log.info("key is {},and value is {}", k, v));
        TimeUnit.SECONDS.sleep(30);
        log.info("sleep 30s");
        orderCache.asMap().forEach((k, v) -> log.info("key is {},and value is {}", k, v));
        TimeUnit.SECONDS.sleep(40);
        log.info("sleep 70s");
        orderCache.asMap().forEach((k, v) -> log.info(" key is {},and value is {}", k, v));
        ctx.close();
    }
}
