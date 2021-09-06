package me.stone.training.platform.training.spring4all.pg.Inf.impl;

import lombok.AllArgsConstructor;
import me.stone.training.platform.training.spring4all.pg.Inf.UserInfoRepo;
import me.stone.training.platform.training.spring4all.pg.Inf.entity.UserInfoEntity;
import me.stone.training.platform.training.spring4all.pg.Inf.mapper.UserInfoMapper;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
public class UserInfoRepoImpl implements UserInfoRepo {

    private UserInfoMapper userInfoMapper;

    @Override
    public int saveUserInfo(String userName, String password, BigDecimal salary, int age, Integer gender) {
        final UserInfoEntity entity = UserInfoEntity.builder()
            .age(age)
            .gender(gender)
            .userName(userName)
            .password(password)
            .build();
        return userInfoMapper.saveUserInfo(entity);
    }

    @Override
    public String getUserInfo(long id) {
        return Optional.ofNullable(userInfoMapper.loadById(id))
            .map(UserInfoEntity::toString)
            .orElse(null);
    }
}
