package com.lihebin.market.wx.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @description: 登录请求参数
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:43 下午
 */
@Setter
@Getter
public class AuthReq {


    /**
     * 管理员名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "码不能为空")
    private String code;

}
