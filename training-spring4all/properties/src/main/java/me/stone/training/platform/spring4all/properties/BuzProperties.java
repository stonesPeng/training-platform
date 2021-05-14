package me.stone.training.platform.spring4all.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 15:43
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "buz")
public class BuzProperties {
    private boolean enable;

    @ConfigurationProperties(prefix = "buz.sub")
    @Setter
    @Getter
    @ToString
    public static class BuzSubProperties {
        private boolean enable;
        private String prop1;
        private Integer prop2;
    }
}
