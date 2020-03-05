package com.lihebin.market.domain;

import com.lihebin.market.bean.Code;
import com.lihebin.market.cache.RedisDao;
import com.lihebin.market.dao.MerchantDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.Merchant;
import com.lihebin.market.params.UserMessage;
import com.lihebin.market.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lihebin on 2019/6/25.
 */
@Service
public class MerchantDomainServiceImpl implements MerchantDomainService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private MerchantDao merchantDao;

    @Override
    @Cacheable(value = "merchant", key = "'merchant_' + #id")
    public Merchant getMerchant(Long id) {
        Optional<Merchant> merchant = merchantDao.findById(id);
        if (!merchant.isPresent()) {
            throw new BackendException(Code.CODE_NOT_EXIST, "商户不存在");
        }
        return merchant.get();
    }

    @Override
    @CacheEvict(value = "merchant", key = "'merchant_' + #id")
    public void removeMerchantCache(Long id) {

    }

    @Override
    public UserMessage getUserMessage(String token) {
        UserMessage userMessage = new UserMessage();
        String value = redisDao.getValue(token);
        if (StringUtil.empty(value)) {
            throw new BackendException(Code.CODE_NOT_EXIST, "用户信息不存在");
        }
        String[] userValue = value.split("-");
        userMessage.setUsername(userValue[0]);
        userMessage.setMerchantId(Long.valueOf(userValue[1]));
        return userMessage;
    }
}
