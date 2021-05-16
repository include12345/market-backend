package com.lihebin.market.wx.domain.req;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description: 专题入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Setter
@Getter
public class TopicReq {

    private Long id;

    /**
     * 专题标题
     */
    private String title;

    /**
     * 专题子标题
     */
    private String subTitle;

    /**
     * 专题内容
     */
    private String content;

    /**
     * 专题相关商品最低价
     */
    private BigDecimal price;

    /**
     * 专题阅读量
     */
    private String readCount;

    /**
     * 专题图片
     */
    private String picUrl;

    /**
     * 专题相关商品，采用JSON数组格式
     */
    private String goods;
}
