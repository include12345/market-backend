package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.CouponReq;
import com.lihebin.market.wx.domain.GoodsReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 商品模块
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @RequiresPermissions("admin:goods:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "查询")
    @GetMapping("/list")
    public Result list(Long goodsId, String goodsSn, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:goods:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "添加")
    @PostMapping("/create")
    public Result create(@RequestBody GoodsReq goodsReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:goods:read")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "详情")
    @GetMapping("/read")
    public Result read(@NotNull Long id) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "编辑")
    @PostMapping("/update")
    public Result update(@RequestBody GoodsReq goodsReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "删除")
    @DeleteMapping("/delete")
    public Result delete(@NotNull Long id) {
        //todo
        return null;
    }
}


