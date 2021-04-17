package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色.
 */
@Repository
public interface RoleDao extends JpaRepository<RoleData, Long>, JpaSpecificationExecutor<RoleData> {


    @Query(value = "select id, `name`, `desc`, enabled, ctime, mtime from role where id in (:ids) and deleted = :deleted", nativeQuery = true)
    List<RoleData> listRoleByIds(@Param(value = "ids")List<Long> ids, @Param(value = "deleted")boolean deleted);
}
