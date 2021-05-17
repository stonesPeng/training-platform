package me.stone.training.platform.spring4all.circular.dependencies.object.case1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 16:47
 */
//@Component
@Slf4j
public class TestBeanA {

    private final TestBeanB testBeanB;

    @Autowired
    public TestBeanA(@Lazy TestBeanB testBeanB) {
        this.testBeanB = testBeanB;
        log.info("construct TestBeanA");
    }

    public void doSomething() {
        log.info("doSomething");
    }
}
