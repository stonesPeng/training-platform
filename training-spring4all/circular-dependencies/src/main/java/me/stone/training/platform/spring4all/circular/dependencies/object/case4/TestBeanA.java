package me.stone.training.platform.spring4all.circular.dependencies.object.case4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

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

    @Autowired
    private TestBeanB testBeanB;

    private ApplicationContext applicationContext;

    public TestBeanA() {
        log.info("TestBeanA construct");
    }


    public void doSomething() {
        log.info("doSomething");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct");
        testBeanB.setTestBeanA(this);
    }
}
