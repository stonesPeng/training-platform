package me.stone.training.platform.spring4all.advice.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 20:01
 */
@ConfigurationProperties(prefix = "encrypt.aes")
@Getter
@Setter
@ToString
public class AesEncryptAdviceProperty {

    private String aesSecret = "asdfghjklzxcvbnm";

    private String encryptJsonKey = "data";
}
