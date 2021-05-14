package me.stone.training.platform.spring4all.advice.endpoint.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/5/14 9:56
 */
@Getter
@Setter
@Builder
@ToString
public class TestBeanDTO {

    private String name;
    private Integer count;
}
