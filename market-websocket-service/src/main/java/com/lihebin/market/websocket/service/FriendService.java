package com.lihebin.market.websocket.service;

import com.lihebin.market.model.UserFriend;
import com.lihebin.market.model.UserFriendReq;
import com.lihebin.market.websocket.domain.Contacts;
import com.lihebin.market.websocket.domain.FriendAdd;
import com.lihebin.market.websocket.domain.FriendUpdate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by lihebin on 2020/6/18.
 */
public interface FriendService {

    /**
     * 获取用户好友列表
     *
     * @param token
     * @return
     */
    List<Map<String, Object>> listFriendsByToken(String token);

    /**
     * 删除好友
     *
     * @param token
     * @param friendName
     */
    boolean deleteFriend(String token, String friendName);

    /**
     * 请求
     *
     * @param token
     * @param friendAdd
     * @return
     */
    boolean addFriendRequest(String token, FriendAdd friendAdd);

    /**
     * 添加好友
     *
     * @param token
     * @param friendAdd
     */
    UserFriend addFriend(String token, FriendAdd friendAdd);

    /**
     * 更新好友信息
     *
     * @param token
     * @param friendUpdate
     */
    boolean updateFriend(String token, FriendUpdate friendUpdate);


    /**
     * 查找用户
     *
     * @param friendName
     * @return
     */
    List<Map<String, Object>> searchFriend(String friendName);

    /**
     * 列出好友列表
     *
     * @param token
     * @return
     */
    List<UserFriendReq> listFriendReqByToken(String token);
}
