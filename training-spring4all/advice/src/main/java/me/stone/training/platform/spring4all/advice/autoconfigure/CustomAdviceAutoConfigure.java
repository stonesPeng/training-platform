package me.stone.training.platform.spring4all.advice.autoconfigure;

import me.stone.training.platform.spring4all.advice.property.AesEncryptAdviceProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 15:46
 */

@Configuration
@EnableConfigurationProperties({
    AesEncryptAdviceProperty.class
})
@Import({
    RequestBodyDecryptAdvice.class,
    ResponseBodyEncryptAdvice.class
})
public class CustomAdviceAutoConfigure {

}
