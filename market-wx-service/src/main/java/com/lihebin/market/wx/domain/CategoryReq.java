package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description: 品牌商请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class CategoryReq {

    /**
     * id
     */
    private Long id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * '类目关键字，以JSON数组格式
     */
    private String keywords;

    /**
     * 类目广告语介绍
     */
    private String desc;


    /**
     * 父类目ID
     */
    private Long pId;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目图片
     */
    private String picUrl;

    /**
     * 类目层级
     */
    private String level;

}
