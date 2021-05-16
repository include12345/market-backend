package com.lihebin.market.wx.domain.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 推广出参
 *
 * Created by lihebin on 2021/5/16.
 */
@Setter
@Getter
public class AdResult {

    private Long id;

    /**
     * 广告标题
     */
    private String name;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    private String link;

    /**
     * 广告宣传图片
     */
    private String url;

    /**
     * 广告位置：1则是首页
     */
    private Integer position;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 广告开始时间
     */
    private Date startTime;

    /**
     * 广告结束时间
     */
    private Date endTime;

    /**
     * 是否启用
     */
    private Boolean enabled;

    private Date ctime;

    private Date mtime;

}
