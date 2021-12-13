package me.stone.training.platform.infra.common.api.exception;

import lombok.Getter;
import me.stone.training.platform.training.common.tool.utils.ArraysUnit;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author honorStone
 * @description default description
 * @date 2021/12/13 17:37
 */
public abstract class CommonException extends RuntimeException {
    public static AtomicBoolean useUserMessageCode = new AtomicBoolean(false);
    @Getter final int code;
    @Getter final String userMessage;

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String userMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = useUserMessageCode.get() ? (userMessage != null && code == null ? 555 : code == null ? 500 : code) : code == null ? 500 : code;
        this.userMessage = userMessage;
    }

    protected CommonException(CommonExceptionBuilder<?, ?> b) {
        this(
            b.message,
            b.cause,
            b.enableSuppression,
            b.writableStackTrace,
            b.code,
            b.userMessage
        );
    }


    public static abstract class CommonExceptionBuilder<C extends CommonException, B extends CommonExceptionBuilder<C, B>> {
        String message;
        Throwable cause;
        boolean enableSuppression;
        boolean writableStackTrace;
        Integer code;
        String userMessage;

        public B cause(Throwable cause) {
            this.cause = cause;
            return self();
        }

        public B writableStackTrace(boolean writableStackTrace) {
            this.writableStackTrace = writableStackTrace;
            return self();
        }

        public B enableSuppression(boolean enableSuppression) {
            this.enableSuppression = enableSuppression;
            return self();
        }

        public B userMessage(String userMessage) {
            this.userMessage = userMessage;
            return self();
        }

        public B message(String message) {
            this.message = message;
            return self();
        }

        public B userMessage(String template, Object... args) {
            final FormattingTuple tuple = MessageFormatter.arrayFormat(template, args);
            this.userMessage = tuple.getMessage();
            if (tuple.getThrowable() != null) {
                this.cause = tuple.getThrowable();
            }
            return self();
        }

        public B message(String template, Object... args) {
            final FormattingTuple tuple = MessageFormatter.arrayFormat(template, args);
            this.message = tuple.getMessage();
            if (tuple.getThrowable() != null) {
                this.cause = tuple.getThrowable();
            }
            return self();
        }

        public B code(int code) {
            this.code = code;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        @Override
        public String toString() {
            return "CommonException.CommonExceptionBuilder(super=" + super.toString() +
                ", message=" + this.message +
                ", code=" + this.code +
                ", userMessage=" + this.userMessage + ")";
        }
    }


    public static <E extends CommonException, B extends CommonExceptionBuilder<E, ?>>
    E buildMessage(B builder, String message, Object... args) {
        return builder.message(message, args).build();
    }

    public static <E extends CommonException, B extends CommonExceptionBuilder<E, ?>>
    E buildUserMessage(B builder, String message, Object... args) {
        return builder.userMessage(message, args).build();
    }

    public static <E extends CommonException, B extends CommonExceptionBuilder<E, ?>>
    E buildMessages(B builder, String sysMessage, List<Object> sysArgs, String userMessage, Object... args) {
        return builder
            .userMessage(userMessage, args)
            .message(sysMessage, sysArgs == null ? ArraysUnit.empty() : sysArgs.toArray())
            .build();
    }
}
