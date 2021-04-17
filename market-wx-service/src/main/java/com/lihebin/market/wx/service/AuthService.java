package com.lihebin.market.wx.service;

import com.lihebin.market.wx.domain.AuthReq;
import com.lihebin.market.wx.domain.AuthResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihebin on 2021/4/14.
 */
public interface AuthService {


    /**
     * 获取校验码
     *
     * @param request
     * @return
     */
    String getKaptcha(HttpServletRequest request);

    /**
     * 管理后台登录
     *
     * @param authReq
     * @param request
     * @return
     */
    AuthResult adminLogin(AuthReq authReq, HttpServletRequest request);
}
