package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 配置模块
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/24 9:05 下午
 */
@RestController
@RequestMapping("/admin/config")
public class ConfigController {


    @RequiresPermissions("admin:config:mall:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "商场配置"}, button = "详情")
    @GetMapping("/mall")
    public Result listMall() {
        return null;
    }

    @RequiresPermissions("admin:config:mall:updateConfigs")
    @RequiresPermissionsDesc(menu = {"配置管理", "商场配置"}, button = "编辑")
    @PostMapping("/mall")
    public Result updateMall(@RequestBody Map<String, String> body) {
        return null;
    }

    @RequiresPermissions("admin:config:express:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "详情")
    @GetMapping("/express")
    public Result listExpress() {
        return null;
    }

    @RequiresPermissions("admin:config:express:updateConfigs")
    @RequiresPermissionsDesc(menu = {"配置管理", "商场配置"}, button = "编辑")
    @PostMapping("/express")
    public Result updateExpress(@RequestBody Map<String, String> body) {
        return null;
    }

    @RequiresPermissions("admin:config:order:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "订单配置"}, button = "详情")
    @GetMapping("/order")
    public Result listOrder() {
        return null;
    }

    @RequiresPermissions("admin:config:order:updateConfigs")
    @RequiresPermissionsDesc(menu = {"配置管理", "订单配置"}, button = "编辑")
    @PostMapping("/order")
    public Result updateOrder(@RequestBody Map<String, String> body) {
        return null;
    }

    @RequiresPermissions("admin:config:wx:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "小程序配置"}, button = "详情")
    @GetMapping("/wx")
    public Result listWx() {
        return null;
    }

    @RequiresPermissions("admin:config:wx:updateConfigs")
    @RequiresPermissionsDesc(menu = {"配置管理", "小程序配置"}, button = "编辑")
    @PostMapping("/wx")
    public Result updateWx(@RequestBody Map<String, String> body) {
        return null;
    }

}
