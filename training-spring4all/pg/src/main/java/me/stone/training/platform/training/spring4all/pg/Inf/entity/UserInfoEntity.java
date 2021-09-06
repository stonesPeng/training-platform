package me.stone.training.platform.training.spring4all.pg.Inf.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity implements Serializable {

    private String id;
    private String userName;
    private String password;
    private Integer gender;
    private int age;
}
