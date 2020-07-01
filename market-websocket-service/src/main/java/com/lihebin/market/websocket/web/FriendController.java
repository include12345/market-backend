package com.lihebin.market.websocket.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.domain.FriendAdd;
import com.lihebin.market.websocket.domain.FriendUpdate;
import com.lihebin.market.websocket.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lihebin on 2020/6/17.
 */
@RestController
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    @Autowired
    private FriendService friendService;


    @RequestMapping(value = "/searchFriend", method = RequestMethod.GET)
    public Result searchFriend(@RequestParam(value = "friendName", required = true) String friendName) {
        return ResultUtil.success(friendService.searchFriend(friendName));
    }

    @RequestMapping(value = "/listFriends", method = RequestMethod.GET)
    public Result listFriends(@RequestHeader("token") String token) {
        return ResultUtil.success(friendService.listFriendsByToken(token));
    }


    @DeleteMapping(value = "/deleteFriend/{friendName}")
    public Result deleteFriend(@RequestHeader("token") String token, @PathVariable String friendName) {
        friendService.deleteFriend(token, friendName);
        return ResultUtil.success(null);
    }


    @RequestMapping(value = "/addFriendRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addFriendRequest(@Valid @RequestHeader("token") String token, @RequestBody FriendAdd friendAdd) {
        return ResultUtil.success(friendService.addFriendRequest(token, friendAdd));
    }

    @RequestMapping(value = "/listFriendReq", method = RequestMethod.GET)
    public Result listFriendReq(@RequestHeader("token") String token) {
        return ResultUtil.success(friendService.listFriendReqByToken(token));
    }

    @RequestMapping(value = "/dealFriendReq", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result dealFriendReq(@Valid @RequestHeader("token") String token, @RequestBody FriendAdd friendAdd) {
        return ResultUtil.success(friendService.addFriend(token, friendAdd));
    }

    @RequestMapping(value = "/updateFriend", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateFriend(@Valid @RequestHeader("token") String token, @RequestBody FriendUpdate friendUpdate) {
        return ResultUtil.success(friendService.updateFriend(token, friendUpdate));
    }
}
