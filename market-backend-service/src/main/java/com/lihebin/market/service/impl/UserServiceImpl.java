package com.lihebin.market.service.impl;

import com.lihebin.market.params.Login;
import com.lihebin.market.params.LoginRes;
import com.lihebin.market.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by lihebin on 2020/3/1.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public LoginRes login(Login login) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
