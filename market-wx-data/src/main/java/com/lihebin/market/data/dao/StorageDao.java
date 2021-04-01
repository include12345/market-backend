package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.StorageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 对象存储.
 */
@Repository
public interface StorageDao extends JpaRepository<StorageData, Long>, JpaSpecificationExecutor<StorageData> {

}
