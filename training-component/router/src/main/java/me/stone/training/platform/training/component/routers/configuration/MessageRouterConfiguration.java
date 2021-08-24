package me.stone.training.platform.training.component.routers.configuration;

import me.stone.training.platform.training.component.routers.handler.LogMessageHandler;
import me.stone.training.platform.training.component.routers.message.MessageRouter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/19 17:09
 */
@Configuration
public class MessageRouterConfiguration {

    final LogMessageHandler logMessageHandler;

    public MessageRouterConfiguration(LogMessageHandler logMessageHandler) {
        this.logMessageHandler = logMessageHandler;
    }

    @Bean
    @ConditionalOnMissingBean(value = MessageRouter.class)
    public MessageRouter newRouter() {
        final MessageRouter messageRouter = new MessageRouter();
        messageRouter.rule().async(false).event("event").handle(logMessageHandler).end();
        //add more
        return messageRouter;
    }
}
