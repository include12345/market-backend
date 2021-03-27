package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 订单服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/order")
public class OrderController {



    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 显示类型，如果是0则是全部订单
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId,
                       @RequestParam(defaultValue = "0") Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 订单详情
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId,
                         @NotNull Long orderId) {
        return null;
    }

    /**
     * 提交订单
     * @param request 订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return
     */
    @PostMapping("/submit")
    public Result submit(@LoginUser Long userId,
                         @RequestBody Map<String, String> request) {
        return null;
    }

    /**
     * 取消订单
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/cancel")
    public Result cancel(@LoginUser Long userId,
                         @RequestBody Map<String, String> request) {
        return null;
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/prepay")
    public Result prepay(@LoginUser Long userId,
                         @RequestBody Map<String, String> body,
                         HttpServletRequest request) {
        return null;
    }

    /**
     * 微信H5支付
     * @param userId
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/h5pay")
    public Result h5pay(@LoginUser Long userId,
                         @RequestBody Map<String, String> body,
                         HttpServletRequest request) {
        return null;
    }

    /**
     * 微信付款成功或失败回调接口
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/payNotify")
    public Result payNotify(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * 订单申请退款
     *
     * @param userId
     * @param body
     * @return
     */
    @PostMapping("/refund")
    public Result refund(@LoginUser Long userId,
                        @RequestBody Map<String, String> body) {
        return null;
    }

    /**
     * 确认收货
     *
     * @param userId
     * @param body
     * @return
     */
    @PostMapping("/confirm")
    public Result confirm(@LoginUser Long userId,
                         @RequestBody Map<String, String> body) {
        return null;
    }

    /**
     * 删除订单
     *
     * @param userId
     * @param body
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@LoginUser Long userId,
                          @RequestBody Map<String, String> body) {
        return null;
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId
     * @param orderId
     * @param goodsId
     * @return
     */
    @GetMapping("/goods")
    public Result goods(@LoginUser Long userId,
                        @NotNull Long orderId,
                        @NotNull Long goodsId) {
        return null;
    }

    /**
     * 评价订单商品
     *
     * @param userId
     * @param body
     * @return
     */
    @PostMapping("/comment")
    public Result comment(@LoginUser Long userId,
                         @RequestBody Map<String, String> body) {
        return null;
    }


}
