package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description: 专题服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/topic")
public class TopicController {



    /**
     * 专题列表
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
     * 专题详情
     *
     * @param userId
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId, @NotNull Long id) {
        return null;
    }

    /**
     * 相关专题
     *
     * @param id
     * @return
     */
    @GetMapping("/related")
    public Result related(@NotNull Long id) {
        return null;
    }

}
