package com.lihebin.market.websocket.web;

import com.lihebin.market.enums.Code;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.CheckUtil;
import com.lihebin.market.websocket.constant.RobotConstant;
import com.lihebin.market.websocket.constant.StompConstant;
import com.lihebin.market.websocket.domain.*;
import com.lihebin.market.websocket.enums.MessageTypeEnum;
import com.lihebin.market.websocket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihebin on 2020/5/30.
 */
@RestController
@Slf4j
public class ChatController {

    @Autowired
    private MessageService messageService;

    /**
     * 聊天室发布订阅
     *
     * @param messageRO
     * @param user
     */
    @MessageMapping(StompConstant.PUB_CHAT_ROOM)
    public void chatRoom(MessageRO messageRO, User user) {
        String message = messageRO.getMessage();
        if (StringUtils.isEmpty(messageRO.getMessage())
                || StringUtils.isEmpty(messageRO.getImage())
                || user == null
                || StringUtils.isEmpty(user.getUserId())) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }

        if (StringUtils.isNotEmpty(message) && message.startsWith(RobotConstant.prefix)) {
            messageService.sendMessageToRobot(StompConstant.SUB_CHAT_ROOM, message, user);
        }

        messageService.sendMessage(StompConstant.SUB_CHAT_ROOM,
                new MessageVO(user,
                        message,
                        messageRO.getImage(),
                        MessageTypeEnum.USER)
        );
    }


    /**
     * 发送消息到指定用户
     *
     * @param messageRO
     * @param user
     */
    @MessageMapping(StompConstant.PUB_USER)
    public void sendToUser(MessageRO messageRO, User user) {
        if (StringUtils.isEmpty(messageRO.getMessage())
                || StringUtils.isEmpty(messageRO.getImage())
                || user == null
                || StringUtils.isEmpty(user.getUserId())) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }

        messageService.sendMessageToUser(messageRO.getReceivers(),
                new MessageVO(user,
                        messageRO.getMessage(),
                        messageRO.getImage(),
                        MessageTypeEnum.USER,
                        messageRO.getReceivers())
        );
    }

    @MessageExceptionHandler(Exception.class)
    public void handleExceptions(Exception e, User user) {
        int code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();
        String message = CodeEnum.INTERNAL_SERVER_ERROR.getDesc();

        if (e instanceof BackendException) {
            code = ((BackendException) e).getCode();
            message = e.getMessage();
        } else {
            log.error("error:", e);
        }
        messageService.sendErrorMessage(code, message, user);
    }


    public void revokeMessage(RevokeMessageRO revokeMessageRO, User user) {
        if (revokeMessageRO == null
                || user == null
                || StringUtils.isEmpty(user.getUserId())) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }
        CheckUtil.checkMessageId(revokeMessageRO.getMessageId(), user.getUserId());
        RevokeMsgVO revokeMsgVO = new RevokeMsgVO();
        revokeMsgVO.setRevokeMessageId(revokeMessageRO.getMessageId());
        revokeMsgVO.setUser(user);
        revokeMsgVO.setType(MessageTypeEnum.REVOKE);
        if ( ArrayUtils.isNotEmpty(revokeMessageRO.getReceivers())) {
            // 将消息发送到指定用户
            messageService.sendMessageToUser(revokeMessageRO.getReceivers(), revokeMsgVO);
            return;
        }
        // 将消息发送到所有用户
        messageService.sendMessage(StompConstant.SUB_CHAT_ROOM, revokeMsgVO);
    }


}
