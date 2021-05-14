package me.stone.training.platform.spring4all.circular.dependencies.object;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 16:47
 */
@Component
@Slf4j
public class TestBeanB {
    private final TestBeanA testBeanA;

    @Autowired
    public TestBeanB(@Lazy TestBeanA testBeanA) {
        this.testBeanA = testBeanA;
        log.info("construct TestBeanB");
    }

    public void doSomething() {
        log.info("doSomething");
    }

}
