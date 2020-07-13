package com.lihebin.market.websocket.web;

import com.alibaba.fastjson.JSON;
import com.lihebin.market.bean.Result;
import com.lihebin.market.enums.Code;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.UserFriendReq;
import com.lihebin.market.utils.CheckUtil;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.constant.RobotConstant;
import com.lihebin.market.websocket.constant.StompConstant;
import com.lihebin.market.websocket.domain.*;
import com.lihebin.market.websocket.enums.MessageTypeEnum;
import com.lihebin.market.websocket.service.ChatRecordService;
import com.lihebin.market.websocket.service.FriendService;
import com.lihebin.market.websocket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by lihebin on 2020/5/30.
 */
@RestController
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ChatRecordService chatRecordService;

    @Autowired
    private FriendService friendService;
//
//    @Autowired
//    private MessageService messageService;

//    /**
//     * 聊天室发布订阅
//     *
//     * @param messageRO
//     * @param user
//     */
//    @MessageMapping(StompConstant.PUB_CHAT_ROOM)
//    public void chatRoom(MessageRO messageRO, User user) {
//        String message = messageRO.getMessage();
//        if (StringUtils.isEmpty(messageRO.getMessage())
//                || StringUtils.isEmpty(messageRO.getImage())
//                || user == null
//                || StringUtils.isEmpty(user.getUserId())) {
//            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
//        }
////
////        if (StringUtils.isNotEmpty(message) && message.startsWith(RobotConstant.prefix)) {
////            messageService.sendMessageToRobot(StompConstant.SUB_CHAT_ROOM, message, user);
////        }
//
////        messageService.sendMessage(StompConstant.SUB_CHAT_ROOM,
////                new MessageVO(user,
////                        message,
////                        messageRO.getImage(),
////                        MessageTypeEnum.SMS)
////        );
//    }


    /**
     * 发送消息到指定用户
     *
     * @param messageVO
     */
    @MessageMapping(StompConstant.SUB_USER)
    public void sendToUser(MessageVO messageVO) {
        if (MessageTypeEnum.HAS_READ.equals(messageVO.getType())) {
            chatRecordService.updateChatRecordReadedStatus(messageVO.getFrom(),
                    messageVO.getTo());
            return;
        }
        messageVO.setType(MessageTypeEnum.SMS);
        chatRecordService.createChatRecord(messageVO);
        template.convertAndSendToUser(messageVO.getTo(), StompConstant.SUB_USER,
                ResultUtil.success(messageVO));
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result listChatRecordPaging() {
        List<UserFriendReq> userFriendReqList = friendService.listFriendReqByToken("test");
        template.convertAndSendToUser("test", StompConstant.SUB_USER,
                new MessageVO("test",
                        JSON.toJSONString(userFriendReqList.get(0)),
                        MessageTypeEnum.ADD_FRIEND));
        return ResultUtil.success();
    }

//    /**
//     * 消息异常处理
//     *
//     * @param e
//     * @param user
//     */
//    @MessageExceptionHandler(Exception.class)
//    public void handleExceptions(Exception e, User user) {
//        int code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();
//        String message = CodeEnum.INTERNAL_SERVER_ERROR.getDesc();
//
//        if (e instanceof BackendException) {
//            code = ((BackendException) e).getCode();
//            message = e.getMessage();
//        } else {
//            log.error("error:", e);
//        }
//        messageService.sendErrorMessage(code, message, user);
//    }


//    /**
//     * 撤回消息
//     *
//     * @param revokeMessageRO
//     * @param user
//     */
//    @MessageMapping(StompConstant.PUB_CHAT_ROOM_REVOKE)
//    public void revokeMessage(RevokeMessageRO revokeMessageRO, User user) {
//        if (revokeMessageRO == null
//                || user == null
//                || StringUtils.isEmpty(user.getUserId())) {
//            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
//        }
//        CheckUtil.checkMessageId(revokeMessageRO.getMessageId(), user.getUserId());
////        RevokeMsgVO revokeMsgVO = new RevokeMsgVO();
////        revokeMsgVO.setRevokeMessageId(revokeMessageRO.getMessageId());
////        revokeMsgVO.setUser(user);
////        revokeMsgVO.setType(MessageTypeEnum.SMS);
////        if ( ArrayUtils.isNotEmpty(revokeMessageRO.getReceivers())) {
////            // 将消息发送到指定用户
////            messageService.sendMessageToUser(revokeMessageRO.getReceivers(), revokeMsgVO);
////            return;
////        }
//        // 将消息发送到所有用户
////        messageService.sendMessage(StompConstant.SUB_CHAT_ROOM, revokeMsgVO);
//    }


}
