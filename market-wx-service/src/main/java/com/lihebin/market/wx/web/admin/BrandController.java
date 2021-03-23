package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.AdReq;
import com.lihebin.market.wx.domain.BrandReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 品牌模块
 */
@RestController
@RequestMapping("/admin/brand")
public class BrandController {

    @RequiresPermissions("admin:brand:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "查询")
    @GetMapping("/list")
    public Result list(String name, String content,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:brand:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "添加")
    @PostMapping("/create")
    public Result create(@RequestBody BrandReq brandReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:brand:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "详情")
    @GetMapping("/read")
    public Result read(@NotNull Long id) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:brand:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "编辑")
    @PostMapping("/update")
    public Result update(@RequestBody BrandReq brandReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:brand:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "删除")
    @DeleteMapping("/delete")
    public Result delete(@NotNull Long id) {
        //todo
        return null;
    }
}


