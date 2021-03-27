package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.AddressReq;
import com.lihebin.market.wx.domain.AfterSaleReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 售后服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/aftersale")
public class AfterSaleController {


    /**
     * 售后列表
     * @param userId
     * @param status
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId,
                       @RequestParam Integer status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }

    /**
     * 售后详情
     * @param userId
     * @param orderId
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId, @NotNull Long orderId) {
        return null;
    }


    /**
     * 申请售后
     *
     * @param userId
     * @param afterSaleReq
     * @return
     */
    @PostMapping("/submit")
    public Result submit(@LoginUser Long userId, @RequestBody AfterSaleReq afterSaleReq) {
        return null;
    }


    /**
     * 取消售后
     * 如果管理员还没有审核，用户可以取消自己的售后申请
     *
     * @param userId
     * @param afterSaleReq
     * @return
     */
    @PostMapping("/cancel")
    public Result cancel(@LoginUser Long userId, @RequestBody AfterSaleReq afterSaleReq) {
        return null;
    }

}
