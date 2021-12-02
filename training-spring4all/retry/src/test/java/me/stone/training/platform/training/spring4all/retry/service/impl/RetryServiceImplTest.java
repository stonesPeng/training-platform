package me.stone.training.platform.training.spring4all.retry.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.training.spring4all.retry.config.CustomRecoveryCallback;
import me.stone.training.platform.training.spring4all.retry.exception.CustomRetryException;
import me.stone.training.platform.training.spring4all.retry.service.RetryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;


@SpringBootTest
@Slf4j
class RetryServiceImplTest {

    @Autowired
    private RetryService retryService;


    @Autowired
    private RetryTemplate retryTemplate;


    @Autowired
    private CustomRecoveryCallback customRecoveryCallback;

    @Test
    void retry() {
        try {
            final String message = retryService.retry();
            log.info("message = "+message);
        } catch (CustomRetryException e) {
            log.error("Error while executing test {}",e.getMessage());
        }
    }

    @Test
    void retryWithoutAnnotation(){
        try {
            String message = retryTemplate.execute(x -> retryService.retryWithoutAnnotation(), customRecoveryCallback);
            log.info("message = "+message);
        } catch (CustomRetryException e) {
            log.error("Error while executing test {}",e.getMessage());
        }
    }

}