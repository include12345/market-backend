package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 优惠券服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/footprint")
public class FootPrintController {



    /**
     * 用户足迹列表
     *
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }




    /**
     * 删除用户足迹
     *
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@LoginUser Long userId,
                          @RequestBody Map<String, String> request) {
        return null;
    }

}
