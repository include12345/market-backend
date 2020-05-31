package com.lihebin.market.websocket.config;

import com.lihebin.market.websocket.constant.StompConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.Resource;

/**
 * Created by lihebin on 2020/5/29.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Resource
    private WebSocketInterceptor webSocketInterceptor;

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播使用/topic, 点对点使用/user
        registry.enableSimpleBroker(StompConstant.STOMP_TOPIC, StompConstant.STOMP_USER);
    }


    /**
     * 注册STOMP的节点，并映射指定的url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint(StompConstant.STOMP_ENDPOINT).withSockJS();
    }


    /**
     * 注册消息拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }
}
