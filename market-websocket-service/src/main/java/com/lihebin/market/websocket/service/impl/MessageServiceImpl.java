package com.lihebin.market.websocket.service.impl;

import com.lihebin.market.bean.Result;
import com.lihebin.market.enums.Code;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.annotation.ChatRecord;
import com.lihebin.market.websocket.constant.StompConstant;
import com.lihebin.market.websocket.domain.MessageVO;
import com.lihebin.market.websocket.domain.User;
import com.lihebin.market.websocket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lihebin on 2020/5/30.
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @ChatRecord
    @Override
    public void sendMessage(String subAddress, MessageVO messageVO) {
        if (StringUtils.isEmpty(subAddress)) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }
        simpMessagingTemplate.convertAndSend(subAddress, ResultUtil.success(messageVO));
    }

    @Override
    public void sendMessageToUser(String[] receivers, MessageVO messageVO) {

        if (ArrayUtils.isEmpty(receivers)) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }
        for (String receiver : receivers) {
            // 将消息发送到指定用户 参数说明：1.消息接收者 2.消息订阅地址 3.消息内容
            simpMessagingTemplate.convertAndSendToUser(receiver, StompConstant.SUB_USER,
                    ResultUtil.success(messageVO));
        }

    }

    @Override
    public void sendErrorMessage(Code code, User user) {
        log.info("发送错误信息 -> {} -> {}", code, user);
        simpMessagingTemplate.convertAndSendToUser(user.getUserId(), StompConstant.SUB_USER,
                ResultUtil.error(code.getCode(), code.getDesc()));
    }

    @Override
    public void sendMessageToRobot(String subAddress, String message, User user) {
        log.info("user: {} -> 发送消息到机器人 -> {}", user, message);
    }

    @Override
    public void sendRobotMessage(String subAddress, String message) {

    }

}
