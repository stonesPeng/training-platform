package me.stone.training.platform.common.application;

import me.stone.training.platform.common.application.business.BizController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/17
 */

@SpringBootApplication
public class TrainingLibJarApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TrainingLibJarApplication.class, args);
    }
}
