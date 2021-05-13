package me.stone.training.platform.spring4all.advice.annotation;

import java.lang.annotation.*;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 15:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableDecryptRequestBody {
}
