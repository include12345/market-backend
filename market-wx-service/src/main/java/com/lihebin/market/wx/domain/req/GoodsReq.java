package com.lihebin.market.wx.domain.req;

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
public class GoodsReq {

    /**
     * id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String goodsSn;


    /**
     * 商品所属类目ID
     */
    private Long catagoryId;

    /**
     * 商品所属品牌id
     */
    private Long brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    private String gallery;


    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    /**
     * 商品分享海报
     */
    private String picUrl;

    /**
     *
     */
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    private Boolean isHot;

    /**
     * 商品单位，例如件、盒
     */
    private String unit;
    /**
     * 专柜价格
     */

    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String detail;

}
