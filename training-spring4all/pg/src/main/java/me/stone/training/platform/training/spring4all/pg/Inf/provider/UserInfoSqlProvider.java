package me.stone.training.platform.training.spring4all.pg.Inf.provider;

import me.stone.training.platform.training.spring4all.pg.Inf.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public interface UserInfoSqlProvider {

    static String buildInsert(@Param(value = "entity") UserInfoEntity entity){
        return new SQL(){{
            INSERT_INTO("user_info");
            VALUES("user_name","#{entity.userName}");
            VALUES("password","#{entity.password}");
            VALUES("age","#{entity.age}");
            if(entity.getGender() != null){
                VALUES("gender","#{entity.gender}");
            }
        }}.toString();
    }

    static String buildLoadById(@Param(value = "id") long id){
        return new SQL(){{
            SELECT("id,user_name,password,gender,age");
            FROM("user_info");
            WHERE("id = #{id}");
        }}.toString();
    }
}
