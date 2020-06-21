package com.lihebin.market.websocket.constant;

/**
 * Created by lihebin on 2020/5/29.
 */
public interface StompConstant {

    /**
     * STOMP的节点
     */
    String STOMP_ENDPOINT = "/websocketchat";

    /**
     * 广播式
     */
    String STOMP_TOPIC = "/topic";

    /**
     * 一对一
     */
    String STOMP_USER = "/user";


    /**
     * 单用户消息订阅地址
     */
    String SUB_USER = "/chat";


    /**
     * 好友请求消息订阅地址
     */
    String SUB_FRIEND_ADD_REQ = "/friendAddReq";

    /**
     * 单用户消息发布地址
     */
    String PUB_USER = "/chat";

    /**
     * 聊天室消息发布地址
     */
    String PUB_CHAT_ROOM = "/chatRoom";

    /**
     * 聊天室消息订阅地址
     */
    String SUB_CHAT_ROOM = "/topic/chatRoom";

    /**
     * 异常消息订阅地址
     */
    String SUB_ERROR = "/error";

    /**
     * 用户上下线状态消息订阅地址
     */
    String SUB_STATUS = "/topic/status";

    /**
     * 聊天室消息撤销
     */
    String PUB_CHAT_ROOM_REVOKE = "/chatRoom/revoke";





}
