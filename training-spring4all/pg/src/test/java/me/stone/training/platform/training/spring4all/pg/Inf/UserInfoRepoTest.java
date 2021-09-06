package me.stone.training.platform.training.spring4all.pg.Inf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserInfoRepoTest {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Test
    void saveUserInfo() {
        final int res = userInfoRepo.saveUserInfo(
            "jim",
            "123456",
            BigDecimal.valueOf(123.3),
            32,
            1
        );
        assertEquals(res,1);
    }

    @Test
    void getUserInfo() {
        String userInfo = userInfoRepo.getUserInfo(1);
        assertNotNull(userInfo);
    }
}