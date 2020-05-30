package com.lihebin.market.websocket.domain;

import com.lihebin.market.utils.DateTimeUtil;
import com.lihebin.market.websocket.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by lihebin on 2020/5/30.
 */
@ToString
@NoArgsConstructor
public class MessageVO implements Serializable{

    private Long timestamp = System.currentTimeMillis();

    /**
     * 用户
     */
    @Getter
    @Setter
    private User user;

    /**
     * 消息
     */
    @Getter
    @Setter
    private String message;


    /**
     * 图片
     */
    @Getter
    @Setter
    private String image;

    /**
     * 消息类型
     */
    @Getter
    @Setter
    private MessageTypeEnum type;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 接收者
     */
    private String[] receiver;

    public MessageVO(User user, String message, String image, MessageTypeEnum type, String[] receiver) {
        this.user = user;
        this.message = message;
        this.image = image;
        this.type = type;
        this.receiver = receiver;
    }

    public MessageVO(User user, String message, String image, MessageTypeEnum type) {
        this.user = user;
        this.message = message;
        this.image = image;
        this.type = type;
    }

    public MessageVO(User user, String message, MessageTypeEnum type) {
        this.user = user;
        this.message = message;
        this.type = type;
    }

    public String getMessageId() {
        return String.format("%s:%d", user.getUserId(), timestamp);
    }

    public String getSendTime() {
        return DateTimeUtil.getDate(timestamp, DateTimeUtil.SEND_TIME_FORMAT);
    }
}
