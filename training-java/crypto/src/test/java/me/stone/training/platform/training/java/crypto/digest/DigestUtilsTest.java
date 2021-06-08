package me.stone.training.platform.training.java.crypto.digest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.stone.training.platform.training.java.string.generator.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@Slf4j
class DigestUtilsTest {

    private static final String randomString = RandomStringGenerator.randomString(20);
    Logger logger = LoggerFactory.getLogger(DigestUtilsTest.class);

    @SneakyThrows
    @Test
    void SHA256() {
        logger.info("from {}", randomString);
        logger.info("to {}", DigestUtils.MD5(randomString.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void SHA512() {
    }

    @Test
    void MD5() {
    }

    @Test
    void SHA1() {
    }
}