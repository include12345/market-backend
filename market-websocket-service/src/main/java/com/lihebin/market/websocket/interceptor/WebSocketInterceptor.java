package com.lihebin.market.websocket.interceptor;

import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.websocket.constant.UserStatus;
import com.lihebin.market.websocket.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

/**
 * Created by lihebin on 2020/5/29.
 */
@Component
@Slf4j
public class WebSocketInterceptor implements ChannelInterceptor{

    @Nullable
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("进入拦截器-> preSend");
        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(stompHeaderAccessor.getCommand())) {
            User user = new User();
            user.setUserId(StringUtil.createUUID());
            user.setUsername(stompHeaderAccessor.getFirstNativeHeader("username"));
            user.setAvatar(stompHeaderAccessor.getFirstNativeHeader("avatar"));
            user.setAddress(stompHeaderAccessor.getFirstNativeHeader("address"));
            user.setStatus(UserStatus.ONLINE);
            stompHeaderAccessor.setUser(user);

            log.info("绑定用户信息-> {}", user);
        }
        return message;
    }
}
