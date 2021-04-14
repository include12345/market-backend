package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.AfterSaleReq;
import com.lihebin.market.wx.domain.UserReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 鉴权服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/auth")
public class AuthController {


    /**
     * 微信登录
     * @return
     */
    @GetMapping("/loginWx")
    public Result loginByWeixin(@RequestBody UserReq userReq) {

        return null;
    }

    /**
     * 账户信息更新
     * @param userId
     * @param userReq
     * @return
     */
    @PostMapping("/profile")
    public Result submit(@LoginUser Long userId, @RequestBody UserReq userReq) {
        return null;
    }

    /**
     * 登出
     * @param userId
     * @return
     */
    @PostMapping("/logout")
    public Result logout(@LoginUser Long userId) {
        return null;
    }


    /**
     * 获取详细信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/info")
    public Result info(@LoginUser Long userId) {
        return null;
    }

}
