package me.stone.training.platform.training.spring4all.pg.Inf.mapper;

import me.stone.training.platform.training.spring4all.pg.Inf.entity.UserInfoEntity;
import me.stone.training.platform.training.spring4all.pg.Inf.provider.UserInfoSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.jetbrains.annotations.NotNull;

@Mapper
public interface UserInfoMapper {


    @InsertProvider(value = UserInfoSqlProvider.class,method = "buildInsert")
    int saveUserInfo(@NotNull @Param(value = "entity") UserInfoEntity entity);

    @SelectProvider(value = UserInfoSqlProvider.class,method = "buildLoadById")
    UserInfoEntity loadById(long id);

}
