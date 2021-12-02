package me.stone.training.platform.training.spring4all.retry.service;

import me.stone.training.platform.training.spring4all.retry.exception.CustomRetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 10:20
 */
public interface RetryService {


    /**
     * 指定异常CustomRetryException重试，重试最大次数为4,重试补偿机制间隔200毫秒
     * 还可以配置exclude,指定异常不充实，默认为空
     * @return result
     * @throws CustomRetryException 指定异常
     */
    @Retryable(value = {CustomRetryException.class},maxAttempts = 4,backoff = @Backoff(200))
    String retry() throws CustomRetryException;

    String retryWithoutAnnotation()throws CustomRetryException;
}
