package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.NoticeReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单管理模块
 */
@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "查询")
    @GetMapping("/list")
    public Result list(String nickname, String consignee, String orderSn,
                       @RequestParam(required = false) Date startTime,
                       @RequestParam(required = false) Date endTime,
                       @RequestParam(required = false) List<Integer> orderStatusList,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }

    /**
     * 查询物流公司
     * @return
     */
    @GetMapping("/channel")
    public Result channel() {
        return null;
    }

    @RequiresPermissions("admin:order:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "详情")
    @GetMapping("/read")
    public Result read(@NotNull Long id) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:order:refund")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "订单退款")
    @PostMapping("/refund")
    public Result refund(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:order:ship")
    @RequiresPermissionsDesc(menu = {"推广管理", "通知管理"}, button = "订单发货")
    @PostMapping("/ship")
    public Result ship(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:order:pay")
    @RequiresPermissionsDesc(menu = {"推广管理", "通知管理"}, button = "订单收款")
    @PostMapping("/pay")
    public Result pay(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:order:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "通知管理"}, button = "订单删除")
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:order:reply")
    @RequiresPermissionsDesc(menu = {"推广管理", "通知管理"}, button = "订单商品回复")
    @PostMapping("/reply")
    public Result reply(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }
}


