package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.req.AfterSaleReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 售后模块
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 10:33 下午
 */
@RestController
@RequestMapping("/admin/aftersale")
public class AfterSaleController {

    @RequiresPermissions("admin:aftersale:list")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "查询")
    @GetMapping("/list")
    public Result list(Long orderId, String afterSaleSn, Integer status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }


    @RequiresPermissions("admin:aftersale:recept")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "审核通过")
    @PostMapping("/recept")
    public Result recept(@RequestBody AfterSaleReq afterSaleReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:aftersale:batch-recept")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "审核批量通过")
    @PostMapping("/batchRecept")
    public Result batchRecept(@RequestBody String ids) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:aftersale:reject")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "审核拒绝")
    @PostMapping("/recept")
    public Result reject(@RequestBody AfterSaleReq afterSaleReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:aftersale:batch-reject")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "审核批量拒绝")
    @PostMapping("/batchReject")
    public Result batchReject(@RequestBody String ids) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:aftersale:refund")
    @RequiresPermissionsDesc(menu = {"商城管理", "售后管理"}, button = "退款")
    @PostMapping("/refund")
    public Result refund(@RequestBody AfterSaleReq afterSaleReq) {
        //todo
        return null;
    }

}
