package com.lihebin.market.auth.web;

import com.lihebin.market.auth.bean.Login;
import com.lihebin.market.auth.bean.UserNew;
import com.lihebin.market.auth.bean.UserUpdate;
import com.lihebin.market.auth.service.UserService;
import com.lihebin.market.bean.Result;
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


    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addUser(@Valid @RequestBody UserNew userNew) {
        return ResultUtil.success(userService.addUser(userNew));
    }

    @RequestMapping(value = "/api/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateUser(@Valid @RequestHeader("token") String token, @RequestBody UserUpdate userUpdate) {
        return ResultUtil.success(userService.updateUser(token, userUpdate));
    }

    @RequestMapping(value = "/api/getUser", method = RequestMethod.GET)
    public Result getUser(@RequestParam(value = "token", required = true) String token) {
        return ResultUtil.success(userService.getUser(token));
    }

}
