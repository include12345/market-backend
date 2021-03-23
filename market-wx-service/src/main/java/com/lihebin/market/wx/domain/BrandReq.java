package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 品牌商请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class BrandReq {

    /**
     * id
     */
    private Long id;

    /**
     * 品牌商名称
     */
    private String name;

    /**
     * 品牌商简介
     */
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    private String picUrl;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    private BigDecimal floorPrice;

}
