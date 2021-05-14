package me.stone.training.platform.spring4all.advice.object;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/5/14 10:01
 */

@Setter
@Getter
@ToString
public class PackageData<T> {
    @NotNull(message = "data not null")
    private T data;
}
