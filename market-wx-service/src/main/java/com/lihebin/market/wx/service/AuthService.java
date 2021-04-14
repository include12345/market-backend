package com.lihebin.market.wx.service;

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
}
