package com.lihebin.market.domain;

import com.lihebin.market.bean.Code;
import com.lihebin.market.dao.MerchantConsumerDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.MerchantConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lihebin on 2019/6/25.
 */
@Service
public class ConsumerDomainServiceImpl implements ConsumerDomainService {

    @Autowired
    private MerchantConsumerDao merchantConsumerDao;

    @Override
    @Cacheable(value = "merchantConsumer", key = "'merchantConsumer_' + #consumerId")
    public MerchantConsumer getMerchantConsumer(Long consumerId) {
        Optional<MerchantConsumer> merchantConsumer = merchantConsumerDao.findById(consumerId);
        if (!merchantConsumer.isPresent()) {
            throw new BackendException(Code.CODE_NOT_EXIST, "商户会员不存在");
        }
        return merchantConsumer.get();
    }

    @Override
    @CacheEvict(value = "merchantConsumer", key = "'merchantConsumer_' + #consumerId")
    public void removeMerchantConsumerCache(Long consumerId) {

    }
}
