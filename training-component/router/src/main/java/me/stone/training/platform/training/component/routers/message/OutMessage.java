package me.stone.training.platform.training.component.routers.message;

import lombok.Builder;
import lombok.ToString;

import java.time.Instant;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/18 15:52
 */
@Builder
@ToString
public class OutMessage {

    private String msgType;

    private String fromUser;

    private String toUser;

    private Instant createTime;
}
