package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.PermissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限.
 */
@Repository
public interface PermissionDao extends JpaRepository<PermissionData, Long>, JpaSpecificationExecutor<PermissionData> {


    @Query(value = "select id, permission, role_id, ctime, mtime from permission where role_id in (:roleIds) and deleted = :deleted", nativeQuery = true)
    List<PermissionData> listPremissionByRoleIds(@Param(value = "roleIds")List<Long> roleIds, @Param(value = "deleted")boolean deleted);
}
