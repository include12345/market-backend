package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.CommentReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 优惠券服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/coupon")
public class CouponController {



    /**
     * 优惠券列表
     *
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 个人优惠券列表
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/userList")
    public Result userList(@LoginUser Long userId,
                           Integer status,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "addTime") String sort,
                           @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param userId
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @GetMapping("/userCartList")
    public Result userCartList(@LoginUser Long userId,
                               Long cartId,
                               Long grouponRulesId) {
        return null;
    }

    /**
     * 优惠券领取
     *
     * @return
     */
    @PostMapping("/receive")
    public Result receive(@LoginUser Long userId,
                          @RequestBody Map<String, String> request) {
        return null;
    }

    /**
     * 优惠券兑换
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/exchange")
    public Result exchange(@LoginUser Long userId,
                          @RequestBody Map<String, String> request) {
        return null;
    }

}
