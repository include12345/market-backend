package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 用户收藏服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/collect")
public class CollectController {


    /**
     * 用户收藏列表
     * @param userId 用户ID
     * @param type   类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId,
                       @NotNull Integer type,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }

    /**
     * 用户收藏添加或删除
     *
     * @return
     */
    @PostMapping("/update")
    public Result update(@LoginUser Long userId, @RequestBody Map<String, String> request) {
        return null;
    }

}
