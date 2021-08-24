package me.stone.training.platform.training.component.routers.message;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/18 15:51
 */
public interface MessageHandler {
    OutMessage handle(Message message, Map<String, Object> context);
}
