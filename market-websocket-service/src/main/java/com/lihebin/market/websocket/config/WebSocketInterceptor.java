package com.lihebin.market.websocket.config;

import com.lihebin.market.bean.Code;
import com.lihebin.market.cache.RedisDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.websocket.constant.UserStatus;
import com.lihebin.market.websocket.domain.MessageVO;
import com.lihebin.market.websocket.domain.User;
import com.lihebin.market.websocket.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.stereotype.Component;

/**
 * Created by lihebin on 2020/5/31.
 */
@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    @Autowired
    private RedisDao redisDao;

    @Nullable
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand stompCommand = accessor.getCommand();
        switch (stompCommand) {
            case CONNECT:
                accessor.setUser(getUser(message));
                break;
            case ABORT:
                break;
            case DISCONNECT:
                break;
            default:
                break;

        }
        return message;
    }


    private User getUser(Message<?> message) {
        NativeMessageHeaderAccessor header = MessageHeaderAccessor.getAccessor(message, NativeMessageHeaderAccessor.class);
        String token = header.getFirstNativeHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new BackendException(Code.CODE_TIME_OUT, "登录超时");
        }
        String username = redisDao.getValue(token);
        if (StringUtils.isEmpty(username)) {
            throw new BackendException(Code.CODE_TIME_OUT, "登录超时");
        }
        User user = new User();
        user.setUserId(StringUtil.createUUID());
        user.setUsername(username);
        user.setToken(token);
        user.setAvatar(header.getFirstNativeHeader("avatar"));
        user.setAddress(header.getFirstNativeHeader("address"));
        user.setStatus(UserStatus.ONLINE);
        return user;
    }

}
