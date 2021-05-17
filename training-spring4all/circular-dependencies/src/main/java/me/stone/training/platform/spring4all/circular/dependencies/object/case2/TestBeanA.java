package me.stone.training.platform.spring4all.circular.dependencies.object.case2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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

    private TestBeanB testBeanB;

    public TestBeanA() {
        log.info("TestBeanA construct");
    }

    @Autowired
    public void setTestBeanB(TestBeanB testBeanB) {
        log.info("setTestBeanB");
        this.testBeanB = testBeanB;
    }

    public void doSomething() {
        log.info("doSomething");
    }
}
