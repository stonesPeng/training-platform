package me.stone.training.platform.training.component.routers.handler;

import com.stone.router.message.Message;
import com.stone.router.message.MessageHandler;
import com.stone.router.message.OutMessage;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/19 17:02
 */
@Component
public class LogMessageHandler implements MessageHandler {
    @Override
    public OutMessage handle(Message message, Map<String, Object> context) {
        System.out.println(message.toString());
        return OutMessage.builder()
            .createTime(Instant.now())
            .fromUser(message.getFromUser())
            .msgType(message.getMsgType())
            .toUser(message.getToUser())
            .build();
    }
}
