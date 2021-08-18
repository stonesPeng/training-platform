package me.stone.training.platform.spring4all.lib.jar.inf.autoconfigure;

import me.stone.training.platform.spring4all.lib.jar.api.BizCommands;
import me.stone.training.platform.spring4all.lib.jar.api.BizQueries;
import me.stone.training.platform.spring4all.lib.jar.core.port.BizRepository;
import me.stone.training.platform.spring4all.lib.jar.core.usecase.BizCommandsImpl;
import me.stone.training.platform.spring4all.lib.jar.core.usecase.BizQueriesImpl;
import me.stone.training.platform.spring4all.lib.jar.inf.repository.BizRepositoryImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/17
 */

@Configuration
public class TrainingSpring4allLibJarInfAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public BizRepository bizRepository() {
        return new BizRepositoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public BizCommands bizCommands(BizRepository bizRepository) {
        return new BizCommandsImpl(bizRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public BizQueries bizQueries(BizRepository bizRepository) {
        return new BizQueriesImpl(bizRepository);
    }
}
