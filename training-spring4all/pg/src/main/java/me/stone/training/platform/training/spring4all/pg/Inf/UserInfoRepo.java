package me.stone.training.platform.training.spring4all.pg.Inf;

import java.math.BigDecimal;

public interface UserInfoRepo {

    int saveUserInfo(String userName,
                     String password,
                     BigDecimal salary,
                     int age,
                     Integer gender);

    String getUserInfo(long id);
}
