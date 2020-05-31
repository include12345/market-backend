package com.lihebin.market.service.impl;

import com.lihebin.market.bean.Code;
import com.lihebin.market.cache.RedisDao;
import com.lihebin.market.dao.MerchantUserDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.MerchantUser;
import com.lihebin.market.params.Login;
import com.lihebin.market.params.LoginRes;
import com.lihebin.market.service.UserService;
import com.lihebin.market.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by lihebin on 2020/3/1.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${salt_password}")
    private String SALT;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private MerchantUserDao merchantUserDao;

    @Override
    public LoginRes login(Login login) {
        MerchantUser merchantUser = merchantUserDao.findMerchantUserByUsername(login.getUsername());
        if (merchantUser == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户名密码错误");
        }
        if (!login.getPassword().equals(merchantUser.getPassword())) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户名密码错误");
        }
        String uuid = UUID.randomUUID().toString();
        String con = login.getUsername().concat(SALT).concat(uuid);
        String sign = MD5Util.getSign(con);
        String value = String.format("%s-%d", login.getUsername(), merchantUser.getMerchant_id());
        redisDao.removeValue(sign);
        redisDao.cacheValue(sign, value, 15, TimeUnit.DAYS);
        LoginRes loginRes = new LoginRes();
        loginRes.setToken(sign);
        loginRes.setMerchantId(merchantUser.getMerchant_id());
        loginRes.setType(merchantUser.getType());
        return loginRes;
    }

    @Override
    public void logout(String token) {
        boolean result = redisDao.removeValue(token);
        if (!result) {
            throw new BackendException(Code.CODE_NOT_EXIST, "退出失败");
        }
    }
}
