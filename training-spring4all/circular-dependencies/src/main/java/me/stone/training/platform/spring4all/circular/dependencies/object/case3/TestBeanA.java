package me.stone.training.platform.spring4all.circular.dependencies.object.case3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class TestBeanA implements InitializingBean, ApplicationContextAware {

    private TestBeanB testBeanB;

    private ApplicationContext applicationContext;

    public TestBeanA() {
        log.info("TestBeanA construct");
    }


    public void doSomething() {
        log.info("doSomething");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.testBeanB = applicationContext.getBean(TestBeanB.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
