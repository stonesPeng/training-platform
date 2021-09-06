package me.stone.training.platform.training.spring4all.pg.Inf.configuration;

import me.stone.training.platform.training.spring4all.pg.Inf.UserInfoRepo;
import me.stone.training.platform.training.spring4all.pg.Inf.impl.UserInfoRepoImpl;
import me.stone.training.platform.training.spring4all.pg.Inf.mapper.UserInfoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@MapperScan(value = "me.stone.training.platform.training.spring4all.pg.Inf.mapper")
public class PgExampleInfConfiguration {

    @Bean
    @ConditionalOnMissingBean
    UserInfoRepo userInfoRepo(UserInfoMapper userInfoMapper){
        return new UserInfoRepoImpl(userInfoMapper);
    }

    @EventListener
    void eventPush(ApplicationContext applicationContext){
        System.out.println("eventPush");
    }
}
