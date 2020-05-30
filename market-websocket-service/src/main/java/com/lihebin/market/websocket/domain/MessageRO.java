package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;

/**
 * Created by lihebin on 2020/5/30.
 */
@Getter
@Setter
@ToString
public class MessageRO implements Serializable {

    /**
     * 接收者
     */
    private String[] receivers;

    /**
     * 消息
     */
    private String message;

    /**
     * 图片
     */
    private String image;

    public String[] getReceivers() {
        return ArrayUtils.clone(receivers);
    }

    public void setReceivers(String[] receivers) {
        this.receivers = ArrayUtils.clone(receivers);
    }
}
