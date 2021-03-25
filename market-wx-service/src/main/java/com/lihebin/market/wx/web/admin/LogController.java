package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 操作日志
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:33 下午
 */
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @RequiresPermissions("admin:log:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "操作日志"}, button = "查询")
    @GetMapping("/list")
    public Result list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }
}
