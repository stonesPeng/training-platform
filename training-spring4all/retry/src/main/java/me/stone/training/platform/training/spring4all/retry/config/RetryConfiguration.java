package me.stone.training.platform.training.spring4all.retry.config;

import me.stone.training.platform.training.spring4all.retry.exception.CustomRetryException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 10:54
 */
@Configuration
public class RetryConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public RetryListenerSupport retryListenerSupport(){
        return new DefaultListenerSupport();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomRecoveryCallback customRecoveryCallback(){
        return new CustomRecoveryCallback();
    }

    @Bean
    @ConditionalOnMissingBean
    public RetryTemplate retryTemplate(RetryListenerSupport retryListenerSupport){
        final SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(4);

        final FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(1000L);

        return RetryTemplate.builder()
            .customPolicy(simpleRetryPolicy)
            .customBackoff(fixedBackOffPolicy)
            .withListener(retryListenerSupport)
            .retryOn(CustomRetryException.class)
            .build();
    }
}
