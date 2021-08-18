package me.stone.training.platform.spring4all.jooq.config;

import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
@Configuration
public class RefJooqConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DefaultDSLContext dsl(DefaultConfiguration defaultConfiguration) {
        return new DefaultDSLContext(defaultConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultConfiguration defaultConfiguration(DataSourceConnectionProvider provider) {
        final DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
        defaultConfiguration.setConnectionProvider(provider);
        return null;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceConnectionProvider dataSourceConnectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }
}
