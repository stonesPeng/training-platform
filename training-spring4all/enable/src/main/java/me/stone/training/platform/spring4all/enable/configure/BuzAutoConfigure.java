package me.stone.training.platform.spring4all.enable.configure;

import me.stone.training.platform.spring4all.enable.service.ICommands;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 13:54
 */
@Configuration
public class BuzAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public ICommands iCommands() {
        return new ICommands() {
        };
    }

}
