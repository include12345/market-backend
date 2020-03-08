package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.service.OrderService;
import com.lihebin.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/1.
 */
@RestController
@RequestMapping("/api/merchant/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/listMerchantOrderPaging", method = RequestMethod.GET)
    public Result listMerchantOrderPaging(@RequestHeader("token") String token,
                                          @RequestParam(value = "orderSn", required = false) String orderSn,
                                             @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "type", required = false) Long type,
                                          @RequestParam(value = "status", required = false) Long status,
                                          @RequestParam(value = "ctimeStart", required = false) Date ctimeStart,
                                             @RequestParam(value = "ctimeEnd", required = false) Date ctimeEnd,
                                             @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "30") int pageSize
    ) {
        return ResultUtil.success(orderService.listOrderPaging(token, Optional.ofNullable(ctimeStart), Optional.ofNullable(ctimeEnd),
                Optional.ofNullable(orderSn), Optional.ofNullable(name), Optional.ofNullable(type), Optional.ofNullable(status),
                pageNo, pageSize));
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public void logout(@RequestParam(value = "token", required = true) String token) {
//        merchantUserService.logout(token);
//    }

    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public Result getOrder(@RequestHeader("token") String token,
                           @RequestParam(value = "orderId") Long orderId
    ) {
        return ResultUtil.success(orderService.getOrder(token, orderId));
    }

    @RequestMapping(value = "/listOrderTransaction", method = RequestMethod.GET)
    public Result listOrderTransaction(@RequestHeader("token") String token,
                           @RequestParam(value = "orderId") Long orderId
    ) {
        return ResultUtil.success(orderService.listOrderTransaction(token, orderId));
    }

}
