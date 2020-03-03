package com.lihebin.market.dao;

import com.lihebin.market.model.MerchantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2019/4/16.
 */
@Repository
public interface MerchantUserDao extends JpaRepository<MerchantUser, Long> {

    /**
     * 获取商户用户账号信息
     *
     * @param username
     * @return
     */
    MerchantUser findMerchantUserByUsername(String username);


}
