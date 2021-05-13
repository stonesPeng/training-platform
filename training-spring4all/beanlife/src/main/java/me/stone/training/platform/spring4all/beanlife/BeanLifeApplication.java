package me.stone.training.platform.spring4all.beanlife;

import me.stone.training.platform.spring4all.beanlife.object.TestBean;
import me.stone.training.platform.spring4all.beanlife.object.TestBeanProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    TestBeanProcessor.class
})
public class BeanLifeApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(BeanLifeApplication.class, args);
        ctx.close();
    }

    @ConditionalOnMissingBean
    @Bean(initMethod = "initFromBean", destroyMethod = "destroyFromBean")
    public TestBean testBean() {
        return new TestBean("testBean") {{
            setName("testName");
        }};
    }
}
