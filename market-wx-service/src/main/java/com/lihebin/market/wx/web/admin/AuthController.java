package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.wx.domain.AuthReq;
import com.lihebin.market.wx.domain.AuthResult;
import com.lihebin.market.wx.service.AuthService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登录登出模块
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 10:33 下午
 */
@RestController
@RequestMapping("/admin/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/kaptcha")
    public Result<String> kaptcha(HttpServletRequest request) {
        return ResultUtil.ok(authService.getKaptcha(request));
    }


    @PostMapping("/login")
    public Result<AuthResult> login(@RequestBody AuthReq authReq, HttpServletRequest request) {
        return ResultUtil.ok(authService.adminLogin(authReq, request));
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        authService.adminLogout();
        return ResultUtil.success();
    }

    @RequiresAuthentication
    @PostMapping("/info")
    public Result info() {
        return ResultUtil.ok(authService.adminInfo());

    }
}
