package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 品牌服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/brand")
public class BrandController {


    /**
     * 品牌列表
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
     * 品牌详情
     * @param id
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@NotNull Long id) {
        return null;
    }

}
