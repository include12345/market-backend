package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by lihebin on 2020/5/29.
 */
@Getter
@Setter
@ToString
public class User implements Principal, Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    private int status;

    @Override
    public String getName() {
        return userId;
    }
}
