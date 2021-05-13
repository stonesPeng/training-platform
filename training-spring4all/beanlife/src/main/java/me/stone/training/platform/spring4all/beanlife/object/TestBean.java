package me.stone.training.platform.spring4all.beanlife.object;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/10 18:02
 */
@Slf4j
public class TestBean implements InitializingBean, DisposableBean, ApplicationContextAware, BeanNameAware {

    private String name;

    public TestBean(String name) {
        log.info("do construct,name is {}", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log.info("set method,name is {}", name);
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean#afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean#destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("BeanNameAware#setApplicationContext");
    }


    @Override
    public void setBeanName(String s) {
        log.info("ApplicationContextAware#setBeanName");
    }

    public void initFromBean() {
        log.info("initFrom@Bean");
    }

    public void destroyFromBean() {
        log.info("destroyFrom@Bean");
    }

    @PostConstruct
    public void customInit() {
        log.info("@PostConstruct");
    }

    @PreDestroy
    public void customDestroy() {
        log.info("@PreDestroy");
    }
}
