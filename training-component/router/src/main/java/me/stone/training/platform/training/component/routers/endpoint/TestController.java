package me.stone.training.platform.training.component.routers.endpoint;

import me.stone.training.platform.training.component.routers.message.Message;
import me.stone.training.platform.training.component.routers.message.MessageRouter;
import me.stone.training.platform.training.component.routers.message.OutMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/19 17:22
 */
@RestController

public class TestController {

    private final MessageRouter router;

    public TestController(MessageRouter router) {
        this.router = router;
    }

    @GetMapping(value = "/route")
    public String routeMessage() {
        final Message message = this.mockMessage();
        final OutMessage outMessage = router.route(message);
        return Optional.ofNullable(outMessage).map(OutMessage::toString).orElse("");
    }

    private Message mockMessage() {
        return Message.builder()
            .content("内容")
            .event("event")
            .msgType("msgType")
            .fromUser("from user")
            .toUser("to user")
            .createTime(Instant.now())
            .build();
    }


}
