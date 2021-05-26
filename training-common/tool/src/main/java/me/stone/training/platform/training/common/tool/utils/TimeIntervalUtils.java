package me.stone.training.platform.training.common.tool.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 时间间隔处理器
 * @since 2021/5/25 20:40
 */
public interface TimeIntervalUtils {

    /**
     * 打印执行方法的时间间隔
     */
    Consumer<JustDoIt> TimeIntervalProcessor = method -> {
        System.out.println("Before sort...");
        final Instant from = Instant.now();
        method.doIt();
        final Instant to = Instant.now();
        System.out.println("Takes " + Duration.between(from, to).toMillis() + " milliseconds");
        System.out.println("After sort...");
    };
}
