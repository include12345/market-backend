package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 常见问题入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Setter
@Getter
public class KeywordReq {

    private Long id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 关键字的跳转链接
     */
    private String url;

    /**
     * 是否是热门关键字
     */
    private Boolean isHot;

    /**
     * 是否是默认关键字
     */
    private Boolean isDefault;
}
