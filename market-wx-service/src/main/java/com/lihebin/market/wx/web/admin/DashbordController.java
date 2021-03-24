package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 首页显示
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/24 9:47 下午
 */
@RestController
@RequestMapping("/admin/dashboard")
public class DashbordController {

    @GetMapping("")
    public Result info() {
        return null;
    }
}
