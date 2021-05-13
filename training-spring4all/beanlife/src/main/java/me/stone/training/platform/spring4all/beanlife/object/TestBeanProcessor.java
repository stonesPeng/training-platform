package me.stone.training.platform.spring4all.beanlife.object;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/10 19:19
 */
@Slf4j
public class TestBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            log.info("BeanPostProcessor#postProcessAfterInitialization : {}", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            log.info("BeanPostProcessor#postProcessBeforeInitialization : {}", beanName);
        }
        return bean;
    }
}
