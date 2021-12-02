package me.stone.training.platform.training.spring4all.retry.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 11:20
 */

@Slf4j
public class CustomRecoveryCallback implements RecoveryCallback<String> {

    @Override
    public String recover(RetryContext retryContext) throws Exception {
        log.info("Default Retry service test,total retry {}",retryContext.getRetryCount());
        return "Error Class :: " + retryContext.getLastThrowable().getClass().getName();
    }
}
