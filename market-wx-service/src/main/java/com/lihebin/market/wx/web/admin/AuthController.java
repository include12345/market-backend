package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.AfterSaleReq;
import com.lihebin.market.wx.domain.AuthReq;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @GetMapping("/kaptcha")
    public Result kaptcha(HttpServletRequest request) {
        //todo
        return null;
    }


    @PostMapping("/login")
    public Result login(@RequestBody AuthReq authReq, HttpServletRequest request) {
        //todo
        return null;
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        //todo
        return null;
    }

    @RequiresAuthentication
    @PostMapping("/info")
    public Result info() {
        //todo
        return null;
    }
}
