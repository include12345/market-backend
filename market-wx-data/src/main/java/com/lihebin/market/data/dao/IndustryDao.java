package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2020/2/29.
 */
@Repository
public interface IndustryDao extends JpaRepository<Industry, Long>, JpaSpecificationExecutor<Industry> {

}
