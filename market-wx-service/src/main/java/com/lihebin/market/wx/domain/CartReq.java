package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description: 购物车入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 7:17 下午
 */
@Getter
@Setter
public class CartReq {

    private Long id;

    /**
     * 用户表的用户ID
     */
    private Long userId;

    /**
     * 商品表的商品ID
     */
    private Long goodsId;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    private Long productId;

    /**
     * 商品货品的价格
     */
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    private Integer number;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    private String specifications;

    /**
     * 购物车中商品是否选择状态
     */
    private Boolean checked;

    /**
     * 商品图片或者商品货品图片
     */
    private String picUrl;
}
