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

    /**
     * aes密钥
     */
    private String aesSecret = "asdfghjklzxcvbnm";

    /**
     * 加密参数的json的key
     */
    private String encryptJsonKey = "data";
}
