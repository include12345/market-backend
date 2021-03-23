package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 10:45 下午
 */
@Setter
@Getter
public class AfterSaleReq {

    /**
     * id
     */
    private Long id;

    /**
     * 售后编号
     */
    private String afterSaleSn;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     */
    private Integer type;

    /**
     * 售后原因
     */
    private String reason;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    private List<String> pictures;

    /**
     * 退款说明
     */
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    private Integer status;
}
