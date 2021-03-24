package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 优惠券请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class CouponReq {

    /**
     * id
     */
    private Long id;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    private String desc;


    /**
     * 优惠券标签，例如新人专用
     */
    private String tag;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券
     */
    private BigDecimal min;

    /**
     * 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张
     */
    private Integer limit;

    /**
     * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换
     */
    private Integer type;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架
     */
    private Integer status;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制
     */
    private Integer goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合
     */
    private String goodsValue;

    /**
     * 优惠券兑换码
     */
    private String code;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期
     */
    private Integer timeType;

    /**
     * 基于领取时间的有效天数days
     */
    private Integer days;

    /**
     * 使用券开始时间
     */
    private Date startTime;

    /**
     * 使用券截至时间
     */
    private Date endTime;

}
