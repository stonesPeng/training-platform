package me.stone.training.platform.training.common.tool.utils;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author honorStone
 * @description default description
 * @date 2021/12/13 17:37
 */
public class MessageComposer {

    private final String msg;

    public MessageComposer(String template, Object... args){
        final FormattingTuple tuple = MessageFormatter.arrayFormat(template, args);
        this.msg = tuple.getMessage();
    }

    @Override
    public String toString() {
        return "Message{" +
            "msg='" + msg + '\'' +
            '}';
    }

    public static void main(String[] args) {
        final MessageComposer message = new MessageComposer("id is {}", 123132);
        System.out.println(message.toString());
    }
}
