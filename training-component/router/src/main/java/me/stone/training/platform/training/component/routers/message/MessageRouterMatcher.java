package me.stone.training.platform.training.component.routers.message;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/19 9:47
 */
public interface MessageRouterMatcher {
    boolean match(Message message);
}
