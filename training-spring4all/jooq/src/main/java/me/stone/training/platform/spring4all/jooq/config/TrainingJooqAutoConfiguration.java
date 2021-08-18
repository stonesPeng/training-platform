package me.stone.training.platform.spring4all.jooq.config;

import org.springframework.context.annotation.Import;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
@Import({
    RefJooqConfiguration.class
})
public class TrainingJooqAutoConfiguration {
}
