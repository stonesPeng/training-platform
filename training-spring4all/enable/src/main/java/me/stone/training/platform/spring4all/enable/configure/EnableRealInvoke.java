package me.stone.training.platform.spring4all.enable.configure;

import me.stone.training.platform.spring4all.enable.service.ICommands;
import me.stone.training.platform.spring4all.enable.service.impl.CommandsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.lang.annotation.*;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 14:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
    EnableRealInvoke.RealInvokeAutoConfigure.class
})
public @interface EnableRealInvoke {


    class RealInvokeAutoConfigure {

        @Bean
        @Primary
        public ICommands iCommands() {
            return new CommandsImpl();
        }
    }
}
