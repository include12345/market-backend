package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description: 评论入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 7:17 下午
 */
@Getter
@Setter
public class CommentReq {

    private Long id;

    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论
     */
    private Long valueId;

    /**
     * 评论类型
     */
    private Integer type;

    /**
     * 评论内容
     */
    private String content;


    /**
     * 管理员回复内容
     */
    private String adminContent;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否含有图片
     */
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    private String picUrls;

    /**
     * 评分， 1-5
     */
    private Integer star;
}
