package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.AdData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 推广
 * Created by lihebin on 2021/5/16.
 */
@Repository
public interface AdDao extends JpaRepository<AdData, Long>, JpaSpecificationExecutor<AdData> {

}
