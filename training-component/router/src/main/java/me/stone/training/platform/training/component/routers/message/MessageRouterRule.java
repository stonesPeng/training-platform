package me.stone.training.platform.training.component.routers.message;

import java.util.*;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/18 15:55
 */
public class MessageRouterRule {

    private boolean async;
    private String event;
    private String msgType;
    private String content;
    private String fromUser;
    private String toUser;

    /**
     * 路由器
     */
    private MessageRouter router;
    /**
     * 匹配器
     */
    private MessageRouterMatcher matcher;
    /**
     * 处理器
     */
    private List<MessageHandler> handlers = new ArrayList<>();
    /**
     * 拦截器
     */
    private List<MessageInterceptor> interceptors = new ArrayList<>();


    public MessageRouterRule(MessageRouter router) {
        this.router = router;
    }

    public MessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    public MessageRouterRule msgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    public MessageRouterRule event(String event) {
        this.event = event;
        return this;
    }

    public MessageRouterRule content(String content) {
        this.content = content;
        return this;
    }

    public MessageRouterRule fromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public MessageRouterRule toUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public MessageRouterRule handler(MessageHandler handler, MessageHandler... otherHandlers) {
        this.handlers.add(handler);
        if (otherHandlers != null && otherHandlers.length > 0) {
            Collections.addAll(this.handlers, otherHandlers);
        }
        return this;
    }

    public MessageRouterRule handle(MessageHandler handler) {
        return this.handler(handler, (MessageHandler[]) null);
    }

    public MessageRouter end() {
        this.router.getRules().add(this);
        return this.router;
    }

    protected boolean test(Message message) {
        //here can use matcher
        return (this.fromUser == null || this.fromUser.equals(message.getFromUser())) && (this.msgType == null || this.msgType.toLowerCase().equals(message.getMsgType() == null ? null : message.getMsgType().toLowerCase())) && (this.event == null || this.event.toLowerCase().equals(message.getEvent() == null ? null : message.getEvent().toLowerCase())) && (this.content == null || this.content.equals(message.getContent() == null ? null : message.getContent().trim())) && (this.matcher == null || this.matcher.match(message));
    }

    protected OutMessage service(Message message, Map<String, Object> context) {
        OutMessage outMessage = null;
        final Iterator<MessageHandler> iterator = handlers.iterator();
        while (iterator.hasNext()) {
            final MessageHandler handler = iterator.next();
            if (null != handler) {
                outMessage = handler.handle(message, context);
            }
        }
        return outMessage;
    }
}
