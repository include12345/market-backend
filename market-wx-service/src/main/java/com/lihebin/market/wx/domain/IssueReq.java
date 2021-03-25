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
public class IssueReq {

    private Long id;

    /**
     * 问题标题
     */
    private String question;

    /**
     * 问题答案
     */
    private String answer;
}
