package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 角色入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Setter
@Getter
public class RoleReq {

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String desc;

    /**
     * 是否启用
     */
    private Boolean enable;

}
