package com.lihebin.market.service.impl;

import com.lihebin.market.params.ConsumerAdd;
import com.lihebin.market.params.ConsumerRes;
import com.lihebin.market.params.ConsumerUpdate;
import com.lihebin.market.service.ConsumerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


/**
 * Created by lihebin on 2019/4/29.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {


    @Override
    public Page<ConsumerRes> listMerchantConsumerPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                                        Optional<String> name, Optional<String> cellphone,
                                                        int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void deleteMerchantConsumer(String token, long id) {

    }

    @Override
    public ConsumerRes getMerchantConsumer(String token, long consumerId) {
        return null;
    }

    @Override
    public ConsumerRes addMerchantConsumer(String token, ConsumerAdd consumerAdd) {
        return null;
    }

    @Override
    public ConsumerRes updateMerchantConsumer(String token, ConsumerUpdate consumerUpdate) {
        return null;
    }
}
