package com.lihebin.market.data.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: 角色
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RoleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column
    private String name;

    /**
     * 角色描述
     */
    @Column
    private String desc;

    /**
     * 是否启用
     */
    @Column
    private Boolean enable;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted = false;


}
