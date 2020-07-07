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
public class MessageVO{

    /**
     * 消息id
     */
    private String messageId;
    /**
     * 用户
     */
    private String from;

    private String to;

    /**
     * 消息
     */
    private String content;


    /**
     * 图片
     */
    private String image;

    /**
     * 消息类型
     */
    private MessageTypeEnum type;

    /**
     * 创建时间
     */
    private long ctime;

    /**
     * 接收者
     */
    private String[] receiver;

    public MessageVO() {
    }

    public MessageVO(String from, String content, String image, MessageTypeEnum type, String[] receiver) {
        this.from = from;
        this.content = content;
        this.image = image;
        this.type = type;
        this.receiver = receiver;
    }

    public MessageVO(String from, String content, String image, MessageTypeEnum type) {
        this.from = from;
        this.content = content;
        this.image = image;
        this.type = type;
    }

    public MessageVO(String from, String content, MessageTypeEnum type) {
        this.from = from;
        this.content = content;
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MessageTypeEnum getType() {
        return type;
    }

    public void setType(MessageTypeEnum type) {
        this.type = type;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String[] getReceiver() {
        return receiver;
    }

    public void setReceiver(String[] receiver) {
        this.receiver = receiver;
    }
}
