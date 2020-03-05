package com.lihebin.market.domain;


import com.lihebin.market.model.MerchantConsumer;

/**
 * Created by lihebin on 2019/6/25.
 */
public interface ConsumerDomainService {


    /**
     * 获取会员信息
     *
     * @param consumerId
     * @return
     */
    MerchantConsumer getMerchantConsumer(Long consumerId);


    /**
     * 清除会员缓存
     *
     * @param consumerId
     */
    void removeMerchantConsumerCache(Long consumerId);

}
