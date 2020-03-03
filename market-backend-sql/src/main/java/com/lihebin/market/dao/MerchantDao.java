package com.lihebin.market.dao;

import com.lihebin.market.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2020/2/29.
 */
@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {
}
