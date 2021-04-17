package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.AdminData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员.
 */
@Repository
public interface AdminDao extends JpaRepository<AdminData, Long>, JpaSpecificationExecutor<AdminData> {


    /**
     * 根据用户名查询
     *
     * @param username
     * @param deleted
     * @return
     */
    List<AdminData> findAllByUsernameAndDeleted(String username, boolean deleted);
}
