package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @description: 首页服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/home")
public class HomeController {


    /**
     * 清空缓存
     * @param key
     * @return
     */
    @GetMapping("/cache")
    public Result cache(@NotNull String key) {
        return null;
    }

    /**
     * 首页数据
     *
     * @param userId
     * @return
     */
    @GetMapping("/index")
    public Result index(@LoginUser Long userId) {
        return null;
    }

    /**
     * 团购规则列表
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 商城介绍
     *
     * @return
     */
    @GetMapping("/about")
    public Result about() {
        return null;
    }

}
