package com.lihebin.market.service;

import com.lihebin.market.params.Login;
import com.lihebin.market.params.LoginRes;

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
}
