package com.lihebin.market.data.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @description: 管理员表
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Table(name = "admin")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AdminData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 管理员名称
     */
    @Column
    private String username;

    /**
     * 管理员密码
     */
    @Column
    private String password;

    /**
     * 最近一次登录IP地址
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login_time")
    private Long lastLoginTime;

    /**
     * 头像图片
     */
    @Column
    private String avatar;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted = false;

    /**
     * 角色列表
     */
    @Column(name = "role_ids")
    private List<Long> roleIds;


}
