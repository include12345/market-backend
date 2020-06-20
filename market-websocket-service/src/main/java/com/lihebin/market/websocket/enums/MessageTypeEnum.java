package com.lihebin.market.websocket.enums;

/**
 * Created by lihebin on 2020/5/30.
 */
public enum MessageTypeEnum {

    /**
     * 消息
     */
    SMS,
    /**
     * 添加好友请求
     */
    ADD_FRIEND,
    /**
     * 处理好友请求
     */
    DEAL_ADD_FRIEND_REQ,
    /**
     * 删除好友
     */
    DELETE_FRIEND,
    /**
     * 挤下线
     */
    PUSH_OUT,
    /**
     * 消息已读
     */
    HAS_READ,
    /**
     * 同意好友请求
     */
    ACCEPTED_FRIEND_REQ
}
