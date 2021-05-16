package com.lihebin.market.wx.domain.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 通知入参
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Setter
@Getter
public class NoticeReq {

    private Long id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 创建通知的管理员ID，如果是系统内置通知则是0
     */
    private Long adminId;

}
