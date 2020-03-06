package com.lihebin.market.service;

import com.lihebin.market.model.MerchantProduct;
import com.lihebin.market.params.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/1.
 */
public interface ProductService {


    /**
     * 获取商户下产品信息
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<ConsumerRes> listProductPaging(String token, Optional<String> name,
                                        Optional<Long> industryId, Optional<Long> status,
                                        Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                        int pageNo, int pageSize);



    /**
     * 删除商户产品
     *
     * @param id
     */
    void deleteProduct(String token, long id);

    /**
     * 获取商户产品
     *
     * @param token
     * @param id
     * @return
     */
    ProductRes getProduct(String token, long id);

    /**
     * 新增商户产品
     *
     * @param token
     * @param productAdd
     * @return
     */
    ProductRes addProduct(String token, ProductAdd productAdd);

    /**
     * 编辑商户产品
     *
     * @param token
     * @param productUpdate
     * @return
     */
    ProductRes updateProduct(String token, ProductUpdate productUpdate);


    /**
     * build ProductRes
     *
     * @param merchantProduct
     * @return
     */
    default ProductRes buildProductRes(MerchantProduct merchantProduct) {
//        ProductRes merchantConsumerRes = new ConsumerRes();
//        merchantConsumerRes.setId(merchantConsumer.getId());
//        merchantConsumerRes.setMerchantId(merchantConsumer.getMerchantId());
//        merchantConsumerRes.setConsumerCellphone(merchantConsumer.getCellphone());
//        merchantConsumerRes.setConsumerEmail(merchantConsumer.getEmail());
//        merchantConsumerRes.setConsumerName(merchantConsumer.getName());
//        merchantConsumerRes.setConsumerWechat(merchantConsumer.getWechat());
//        merchantConsumerRes.setCtime(merchantConsumer.getCtime());
//        merchantConsumerRes.setMtime(merchantConsumer.getMtime());
//        return merchantConsumerRes;
        return null;
    }

    Page<IndustryRes> listIndustryPaging(Optional<String> level1, Optional<String> level2,
                                         Optional<String> level3, Optional<String> level4,
                                         int pageNo, int pageSize);


    IndustryRes getIndustry(long industryId);


    void deleteIndustry(long id);

    IndustryRes addIndustry(IndustryAdd industryAdd);

    IndustryRes updateIndustry(IndustryUpdate industryUpdate);

}
