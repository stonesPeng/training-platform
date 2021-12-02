package me.stone.training.platform.training.spring4all.retry.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.training.spring4all.retry.exception.CustomRetryException;
import me.stone.training.platform.training.spring4all.retry.service.RetryService;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 10:24
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

    private static int count = 1;

    @Override
    public String retry() throws CustomRetryException {
        log.info("retry{},throw CustomRetryException in method retry",count);
        count ++;
        throw new CustomRetryException("throw custom exception");
    }

    @Override
    public String retryWithoutAnnotation() throws CustomRetryException {
        log.info("retry{},throw CustomRetryException in method retryWithoutAnnotation",count);
        count ++;
        throw new CustomRetryException("throw custom exception in method retryWithoutAnnotation");
    }

    @Recover
    public String recover(Throwable throwable) {
        log.info("Default Retry service test");
        return "Error Class :: " + throwable.getClass().getName();
    }

}
