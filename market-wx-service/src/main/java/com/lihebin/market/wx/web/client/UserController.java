package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 用户服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/user")
public class UserController {


    /**
     * 用户个人页面数据
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId) {
        return null;
    }

}
