package me.stone.training.platform.training.component.routers.message;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/19 10:07
 */
public interface MessageInterceptor {

    OutMessage handle(Message message, Map<String, Object> context);
}
