package me.stone.training.platform.spring4all.advice.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.spring4all.advice.annotation.EnableDecryptRequestBody;
import me.stone.training.platform.spring4all.advice.annotation.EnableEncryptResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/4/17 20:16
 */
@RestController
@RequestMapping(value = "/advice")
@Slf4j
public class AdviceTestController {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping("/bean")
    @EnableDecryptRequestBody
    @EnableEncryptResponseBody
    public String postTestBean(@RequestBody TestBean testBean) {
        log.info("post test Bean of {}", testBean);
        return objectMapper.writeValueAsString(testBean);
    }

    @Getter
    @Setter
    @Builder
    @ToString
    static class TestBean {
        private String name;
        private Integer count;
    }
}
