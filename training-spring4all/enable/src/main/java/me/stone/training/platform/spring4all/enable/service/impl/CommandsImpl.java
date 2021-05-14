package me.stone.training.platform.spring4all.enable.service.impl;

import me.stone.training.platform.spring4all.enable.service.ICommands;
import org.slf4j.helpers.MessageFormatter;

import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 11:58
 */
public class CommandsImpl implements ICommands {
    /**
     * 通过id获取bean
     *
     * @param id id
     * @return bean
     */
    @Override
    public Optional<String> fetchValueById(long id) {
        return Optional.of(MessageFormatter.arrayFormat("value by {}", new Object[]{id}).getMessage());
    }
}
