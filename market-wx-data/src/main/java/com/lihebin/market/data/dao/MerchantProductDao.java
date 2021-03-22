package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.MerchantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2020/2/29.
 */
@Repository
public interface MerchantProductDao extends JpaRepository<MerchantProduct, Long>, JpaSpecificationExecutor<MerchantProduct> {

    /**
     * 根据name查询商品是否存在
     *
     * @param merchantId
     * @param name
     * @return
     */
    MerchantProduct findByMerchantIdAndName(long merchantId, String name);

}
