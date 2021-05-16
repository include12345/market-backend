package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.req.CategoryReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 类目模块
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @RequiresPermissions("admin:category:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "查询")
    @GetMapping("/list")
    public Result list() {
        //todo
        return null;
    }

    @RequiresPermissions("admin:category:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "添加")
    @PostMapping("/create")
    public Result create(@RequestBody CategoryReq categoryReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:category:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "详情")
    @GetMapping("/read")
    public Result read(@NotNull Long id) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "编辑")
    @PostMapping("/update")
    public Result update(@RequestBody CategoryReq categoryReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "删除")
    @DeleteMapping("/delete")
    public Result delete(@NotNull Long id) {
        //todo
        return null;
    }
}


