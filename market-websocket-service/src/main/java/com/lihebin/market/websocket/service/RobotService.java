package com.lihebin.market.websocket.service;

/**
 * Created by lihebin on 2020/5/30.
 */
public interface RobotService {

    /**
     * 发送消息到机器人
     *
     * @param userId
     * @param text
     * @return
     */
    String sendMessage(String userId, String text);
}
