package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.AddressReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 用户收货地址接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/26 10:35 下午
 */
@RestController
@RequestMapping("/client/address")
public class AddressController {


    /**
     * 收货地址列表
     * @param userId
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId) {
        return null;
    }

    /**
     * 收货地址详情
     * @param userId
     * @param id
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId, @NotNull Long id) {
        return null;
    }

    /**
     * 添加或更新收货地址
     *
     * @param userId  用户ID
     * @param addressReq 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("/save")
    public Result save(@LoginUser Long userId, @RequestBody AddressReq addressReq) {
        return null;
    }

    /**
     * 删除收货地址
     *
     * @param userId  用户ID
     * @param addressReq 用户收货地址，{ id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("/delete")
    private Result delete(@LoginUser Long userId, @RequestBody AddressReq addressReq) {
        return null;
    }
}
