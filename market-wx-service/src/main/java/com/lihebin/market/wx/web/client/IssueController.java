package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @description: 团购服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/issue")
public class IssueController {



    /**
     * 帮助中心
     *
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(String question,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 团购活动详情
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId,
                         @NotNull Long grouponId) {
        return null;
    }

    /**
     * 参加团购
     * @param grouponId
     * @return
     */
    @GetMapping("/join")
    public Result join(@NotNull Long grouponId) {
        return null;
    }

    /**
     * 用户开团或入团情况
     *
     * @param showType 显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购
     * @return
     */
    @GetMapping("/my")
    public Result my(@LoginUser Long userId,
                          @RequestParam(defaultValue = "0") Integer showType) {
        return null;
    }

}
