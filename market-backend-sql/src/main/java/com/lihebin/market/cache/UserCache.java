package com.lihebin.market.cache;

import com.lihebin.market.bean.Code;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2020/6/18.
 */
@Repository
public class UserCache {

    @Autowired
    private RedisDao redisDao;

    public String getUsername(String token) {

        String value = redisDao.getValue(token);
        if (StringUtil.empty(value)) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户信息不存在");
        }
        String[] userValue = value.split("-");
        return userValue[0];
    }
}
