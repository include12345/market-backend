package com.lihebin.market.auth.service;

import com.lihebin.market.auth.bean.*;
import com.lihebin.market.bean.Code;
import com.lihebin.market.cache.RedisDao;
import com.lihebin.market.cache.UserCache;
import com.lihebin.market.dao.MerchantUserDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.MerchantUser;
import com.lihebin.market.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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

    @Autowired
    private UserCache userCache;

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
        loginRes.setImageUrl(merchantUser.getImageUrl());
        return loginRes;
    }

    @Override
    public void logout(String token) {
        boolean result = redisDao.removeValue(token);
        if (!result) {
            throw new BackendException(Code.CODE_NOT_EXIST, "退出失败");
        }
    }

    @Transactional
    @Override
    public UserRes addUser(@Valid UserNew userNew) {
        MerchantUser merchantUser = merchantUserDao.findMerchantUserByUsername(userNew.getUsername());
        if (merchantUser != null) {
            throw new BackendException(Code.CODE_EXIST, "用户已存在");
        }
        merchantUser = new MerchantUser();
        merchantUser.setUsername(userNew.getUsername());
        merchantUser.setPassword(userNew.getPassword());
        merchantUserDao.save(merchantUser);
        UserRes userRes = new UserRes();
        userRes.setUsername(userNew.getUsername());
        return userRes;
    }


    @Transactional
    @Override
    public UserRes updateUser(@Valid String token, UserUpdate userUpdate) {
        String username = userCache.getUsername(token);
        if (username == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户不存在");
        }
        MerchantUser merchantUser = merchantUserDao.findMerchantUserByUsername(username);
        if (merchantUser == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户不存在");
        }
        if (!StringUtils.isEmpty(userUpdate.getPassword())) {
            merchantUser.setPassword(userUpdate.getPassword());
        }
        if (!StringUtils.isEmpty(userUpdate.getImageUrl())) {
            merchantUser.setImageUrl(userUpdate.getImageUrl());
        }
        merchantUserDao.save(merchantUser);
        UserRes userRes = new UserRes();
        userRes.setUsername(username);
        userRes.setImageUrl(userUpdate.getImageUrl());
        return userRes;
    }

    @Override
    public UserRes getUser(String token) {
        return null;
    }
}
