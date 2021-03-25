package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @description: 行政区域
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 10:23 下午
 */
@RestController
@RequestMapping("/admin/region")
public class RegionController {


    @GetMapping("/clist")
    public Result clist(@NotNull Long id) {
        return null;
    }


    @GetMapping("/list")
    public Result list() {
        return null;
    }


}
