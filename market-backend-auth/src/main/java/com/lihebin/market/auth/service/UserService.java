package com.lihebin.market.auth.service;


import com.lihebin.market.auth.bean.*;

import javax.validation.Valid;

/**
 * Created by lihebin on 2020/3/1.
 */
public interface UserService {

    /**
     * 登录
     *
     * @param login
     * @return
     */
    LoginRes login(Login login);

    /**
     * 登出
     *
     * @param token
     */
    void logout(String token);

    /**
     * 添加用户
     *
     * @param userNew
     * @return
     */
    UserRes addUser(@Valid UserNew userNew);

    /**
     * 修改用户信息
     *
     * @param token
     * @param userUpdate
     * @return
     */
    UserRes updateUser(@Valid String token, UserUpdate userUpdate);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    UserRes getUser(String token);
}
