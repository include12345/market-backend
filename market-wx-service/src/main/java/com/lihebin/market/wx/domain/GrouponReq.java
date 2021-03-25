package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description: 团购活动请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class GrouponReq {

    /**
     * id
     */
    private Long id;

    /**
     * 关联的订单ID
     */
    private Long orderId;


    /**
     * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     */
    private Long grouponId;

    /**
     * 团购规则ID
     */
    private Long rulesId;

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 团购分享图片地址
     */
    private String shareUrl;

    /**
     * 开团用户ID
     */
    private Long creatorUserId;

    /**
     * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
     */
    private Integer status;

}
