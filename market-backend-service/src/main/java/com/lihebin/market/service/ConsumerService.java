package com.lihebin.market.service;

import com.lihebin.market.model.MerchantConsumer;
import com.lihebin.market.params.ConsumerAdd;
import com.lihebin.market.params.ConsumerRes;
import com.lihebin.market.params.ConsumerUpdate;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/1.
 */
public interface ConsumerService {


    /**
     * 获取商户下会员信息
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<ConsumerRes> listMerchantConsumerPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                                 Optional<String> name, Optional<String> cellphone,
                                                 int pageNo, int pageSize);



    /**
     * 删除商户会员
     *
     * @param id
     */
    void deleteMerchantConsumer(String token, long id);

    /**
     * 获取商户会员余额信息
     *
     * @param token
     * @param consumerId
     * @return
     */
    ConsumerRes getMerchantConsumer(String token, long consumerId);

    /**
     * 新增商户会员
     *
     * @param token
     * @param consumerAdd
     * @return
     */
    ConsumerRes addMerchantConsumer(String token, ConsumerAdd consumerAdd);

    /**
     * 编辑商户会员
     *
     * @param token
     * @param consumerUpdate
     * @return
     */
    ConsumerRes updateMerchantConsumer(String token, ConsumerUpdate consumerUpdate);


    /**
     * build MerchantConsumerRes
     *
     * @param merchantConsumer
     * @return
     */
    default ConsumerRes buildMerchantConsumerRes(MerchantConsumer merchantConsumer) {
        ConsumerRes merchantConsumerRes = new ConsumerRes();
        merchantConsumerRes.setId(merchantConsumer.getId());
        merchantConsumerRes.setMerchantId(merchantConsumer.getMerchantId());
        merchantConsumerRes.setConsumerCellphone(merchantConsumer.getCellphone());
        merchantConsumerRes.setConsumerEmail(merchantConsumer.getEmail());
        merchantConsumerRes.setConsumerName(merchantConsumer.getName());
        merchantConsumerRes.setConsumerWechat(merchantConsumer.getWechat());
        merchantConsumerRes.setCtime(merchantConsumer.getCtime());
        merchantConsumerRes.setMtime(merchantConsumer.getMtime());
        return merchantConsumerRes;
    }

}
