package me.stone.training.platform.spring4all.advice;


import me.stone.training.platform.spring4all.advice.autoconfigure.CustomAdviceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 20:10
 */
@SpringBootApplication
@Import({
    CustomAdviceAutoConfigure.class
})
public class EncryptSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncryptSpringbootApplication.class, args);
    }
}
