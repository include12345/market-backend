package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知管理员模块
 */
@RestController
@RequestMapping("/admin/profile")
public class ProfileController {


    @PostMapping("/password")
    public Result create(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @GetMapping("/nnotice")
    public Result nNotice() {
        //todo
        return null;
    }

    @GetMapping("/lsnotice")
    public Result lsNotice(String title, String type,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "addTime") String sort,
                           @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }

    @PostMapping("/catnotice")
    public Result catNotice(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @PostMapping("/bcatnotice")
    public Result bCatNotice(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @PostMapping("/rmnotice")
    public Result rmNotice(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }

    @PostMapping("/brmnotice")
    public Result brmNotice(@RequestBody Map<String, String> request) {
        //todo
        return null;
    }


}


