package me.stone.training.platform.spring4all.enable.service;

import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 11:55
 */
public interface ICommands {

    /**
     * 通过id获取bean
     *
     * @param id id
     * @return bean
     */
    default Optional<String> fetchValueById(long id) {
        return Optional.of("default value");
    }
}
