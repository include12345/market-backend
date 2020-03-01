package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.params.Login;
import com.lihebin.market.service.UserService;
import com.lihebin.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lihebin on 2019/5/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@Valid @RequestBody Login login) {
        return ResultUtil.success(userService.login(login));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(@RequestParam(value = "token", required = true) String token) {
        userService.logout(token);
        return ResultUtil.success(null);
    }


    @RequestMapping(value = "/api/check", method = RequestMethod.GET)
    public Result checkToken() {
        return ResultUtil.success(null);
    }


}
