package me.stone.training.platform.training.component.routers.message;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/18 15:37
 */

@Builder
@Getter
@ToString
public class Message {
    private String event;
    private String msgType;
    private String content;
    private String fromUser;
    private String toUser;
    private Instant createTime;
    //... as you wish
}
