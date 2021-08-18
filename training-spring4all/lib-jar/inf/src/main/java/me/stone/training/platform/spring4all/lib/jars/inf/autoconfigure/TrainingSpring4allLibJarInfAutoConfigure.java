package me.stone.training.platform.spring4all.lib.jars.inf.autoconfigure;

import me.stone.training.platform.spring4all.lib.jars.api.BizCommands;
import me.stone.training.platform.spring4all.lib.jars.api.BizQueries;
import me.stone.training.platform.spring4all.lib.jars.core.port.BizRepository;
import me.stone.training.platform.spring4all.lib.jars.core.usecase.BizCommandsImpl;
import me.stone.training.platform.spring4all.lib.jars.core.usecase.BizQueriesImpl;
import me.stone.training.platform.spring4all.lib.jars.inf.repository.BizRepositoryImpl;
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
