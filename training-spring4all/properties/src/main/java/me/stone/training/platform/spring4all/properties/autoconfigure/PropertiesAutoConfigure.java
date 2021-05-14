package me.stone.training.platform.spring4all.properties.autoconfigure;

import me.stone.training.platform.spring4all.properties.BuzProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 15:56
 */
@Configuration
@EnableConfigurationProperties({BuzProperties.class, BuzProperties.BuzSubProperties.class})
public class PropertiesAutoConfigure {
}
