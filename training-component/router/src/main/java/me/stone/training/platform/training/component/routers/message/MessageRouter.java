package me.stone.training.platform.training.component.routers.message;

import lombok.Getter;

import java.util.*;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/18 15:50
 */
public class MessageRouter {

    @Getter
    private final List<MessageRouterRule> rules = new ArrayList<>();

    public MessageRouterRule rule() {
        return new MessageRouterRule(this);
    }

    private OutMessage route(Message message, Map<String, Object> context) {
        final List<MessageRouterRule> matchRules = new ArrayList<>();
        final Iterator<MessageRouterRule> iterator = this.rules.iterator();
        while (iterator.hasNext()) {
            final MessageRouterRule rule = iterator.next();
            if (rule.test(message)) {
                matchRules.add(rule);
            }
        }
        if (matchRules.size() == 0) {
            return null;
        } else {
            final Iterator<MessageRouterRule> matchIterator = matchRules.iterator();
            while (matchIterator.hasNext()) {
                final MessageRouterRule rule = matchIterator.next();
                //think think multi  OutMessage
                return rule.service(message, context);
            }
        }
        return null;
    }

    public OutMessage route(Message message) {
        return this.route(message, new HashMap<>(2));
    }
}
