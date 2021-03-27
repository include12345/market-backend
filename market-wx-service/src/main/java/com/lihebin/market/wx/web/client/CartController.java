package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.AddressReq;
import com.lihebin.market.wx.domain.CartReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 用户收货地址接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/26 10:35 下午
 */
@RestController
@RequestMapping("/client/address")
public class CartController {


    /**
     * 用户购物车信息
     * @param userId
     * @return
     */
    @GetMapping("/index")
    public Result index(@LoginUser Long userId) {
        return null;
    }

    /**
     * 加入购物车
     * @param userId
     * @param cartReq
     *
     * @return
     */
    @PostMapping("/add")
    public Result add(@LoginUser Long userId, @RequestBody CartReq cartReq) {
        return null;
    }

    /**
     * 立即购买
     *
     * @param userId
     * @param cartReq
     * @return
     */
    @PostMapping("/fastadd")
    public Result fastadd(@LoginUser Long userId, @RequestBody CartReq cartReq) {
        return null;
    }


    /**
     * 修改购物车商品货品数量
     *
     * @param userId
     * @param cartReq
     * @return
     */
    @PostMapping("/update")
    public Result update(@LoginUser Long userId, @RequestBody CartReq cartReq) {
        return null;
    }


    /**
     * 购物车商品货品勾选状态
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/checked")
    public Result checked(@LoginUser Long userId, @RequestBody Map<String, String> request) {
        return null;
    }

    /**
     * 购物车商品删除
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@LoginUser Long userId, @RequestBody Map<String, String> request) {
        return null;
    }

    /**
     * 购物车商品货品数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/goodscount")
    public Result goodscount(@LoginUser Long userId) {
        return null;
    }

    /**
     * 购物车下单
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/checkout")
    public Result checkout(@LoginUser Long userId, @RequestBody Map<String, String> request) {
        return null;
    }


}
