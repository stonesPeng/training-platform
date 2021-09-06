package me.stone.training.platform.training.spring4all.pg;

import me.stone.training.platform.training.spring4all.pg.Inf.configuration.PgExampleInfConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    PgExampleInfConfiguration.class
})
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }
}
