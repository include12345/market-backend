package com.lihebin.market.dao;

import com.lihebin.market.model.MerchantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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


    @Query(value = "select username, imageUrl, nickname from merchant_user where username != ?1 and username like ?2", nativeQuery = true)
    List<Map<String, Object>> listUserByUsernameLikeAndNoOwn(String username, String friendName);


}
