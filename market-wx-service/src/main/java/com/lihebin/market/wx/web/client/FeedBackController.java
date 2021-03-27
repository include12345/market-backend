package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description: 意见反馈服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/feedback")
public class FeedBackController {


    /**
     * 添加意见反馈
     *
     * @return
     */
    @PostMapping("/submit")
    public Result submit(@LoginUser Long userId,
                          @RequestBody Map<String, String> request) {
        return null;
    }


}
