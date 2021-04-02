package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.MerchantConsumer;
import com.lihebin.market.data.model.StorageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 对象存储.
 */
@Repository
public interface StorageDao extends JpaRepository<StorageData, Long>, JpaSpecificationExecutor<StorageData> {

    /**
     * 根据文件的唯一索引查询文件是否存在
     *
     * @param key
     * @return
     */
    StorageData findDistinctByKeyAndDeleted(String key, boolean deleted);

    /**
     * 根据id查询
     *
     * @param id
     * @param deleted
     * @return
     */
    StorageData findByIdAndDeleted(long id, boolean deleted);
}
