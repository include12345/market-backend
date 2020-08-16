package com.lihebin.market.auth.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by lihebin on 2020/8/16.
 */
@Getter
@Setter
public class UserNew {


    @NotNull
    private String username;

    @NotNull
    private String password;

}
