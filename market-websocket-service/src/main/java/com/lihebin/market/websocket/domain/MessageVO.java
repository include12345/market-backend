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
     * 消息id
     */
    @Getter
    @Setter
    private String messageId;
    /**
     * 用户
     */
    @Getter
    @Setter
    private String from;

    @Getter
    @Setter
    private String to;

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
     * 创建时间
     */
    @Getter
    @Setter
    private long ctime;

    /**
     * 接收者
     */
    private String[] receiver;

    public MessageVO(String from, String message, String image, MessageTypeEnum type, String[] receiver) {
        this.from = from;
        this.message = message;
        this.image = image;
        this.type = type;
        this.receiver = receiver;
    }

    public MessageVO(String from, String message, String image, MessageTypeEnum type) {
        this.from = from;
        this.message = message;
        this.image = image;
        this.type = type;
    }

    public MessageVO(String from, String message, MessageTypeEnum type) {
        this.from = from;
        this.message = message;
        this.type = type;
    }
}
