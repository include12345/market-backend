package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @description: 管理员请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class AdminReq {

    /**
     * id
     */
    private Long id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 角色列表
     */
    private List<Long> roleIds;

}
