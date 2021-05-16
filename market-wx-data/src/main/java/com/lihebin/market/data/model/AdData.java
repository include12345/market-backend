package com.lihebin.market.data.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 广告表
 * Created by lihebin on 2021/5/16.
 */
@Table(name = "ad")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AdData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 广告标题
     */
    @Column
    private String name;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    @Column
    private String link;

    /**
     * 广告宣传图片
     */
    @Column
    private String url;

    /**
     * 广告位置：1则是首页
     */
    @Column
    private Integer position;

    /**
     * 活动内容
     */
    @Column
    private String content;

    /**
     * 广告开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 广告结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 是否启用
     */
    @Column
    private Boolean enabled = false;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted = false;

}
