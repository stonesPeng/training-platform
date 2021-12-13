package me.stone.training.platform.infra.common.api.exception;

import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;


/**
 * @author honorStone
 * @description default description
 * @date 2021/12/13 17:39
 */
@SuperBuilder
public class InvalidException extends CommonException {
    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException() {
        super(null, null, false, false, 500, null);
    }

    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException(String message) {
        super(message, null, false, false, 500, null);
    }

    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException(int code, String userMessage) {
        super(null, null, false, false, code, userMessage);
    }

    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException(String message, int code, String userMessage) {
        super(message, null, false, false, code, userMessage);
    }

    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException(String message, Throwable cause, int code, String userMessage) {
        super(message, cause, false, false, code, userMessage);
    }

    /**
     * @deprecated should use builder
     */
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.1.3")
    public InvalidException(Throwable cause, int code, String userMessage) {
        super(null, cause, false, false, code, userMessage);
    }


    public InvalidException(String message, Throwable cause, Boolean enableSuppression, Boolean writableStackTrace, Integer code, String userMessage) {
        super(message, cause, enableSuppression != null && enableSuppression, writableStackTrace == null || writableStackTrace, code, userMessage);
    }


    public static InvalidException message(String message, Object... args) {
        return CommonException.buildMessage(InvalidException.builder()
            .code(400), message, args);
    }

    public static InvalidException userMessage(String message, Object... args) {
        return CommonException.buildUserMessage(InvalidException.builder()
            .code(400), message, args);
    }

    public static InvalidException message(int code, String message, Object... args) {
        return CommonException.buildMessage(InvalidException.builder()
            .code(code), message, args);
    }

    public static InvalidException userMessage(int code, String message, Object... args) {
        return CommonException.buildUserMessage(InvalidException.builder()
            .code(code), message, args);
    }

    public static InvalidException messages(int code, String sysMessage, List<Object> sysArgs, String userMessage, Object... args) {
        return CommonException.buildMessages(InvalidException.builder().code(code),
            sysMessage,
            sysArgs,
            userMessage,
            args);
    }


    public static void main(String[] args) {
        throw InvalidException.builder().message("oho {}", "123").build();
    }

}