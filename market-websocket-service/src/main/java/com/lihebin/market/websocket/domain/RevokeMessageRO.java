package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;

/**
 * Created by lihebin on 2020/5/30.
 */
@Setter
@Getter
@ToString
public class RevokeMessageRO implements Serializable {

    /**
     * 接收者
     */
    private String[] receivers;

    /**
     * 消息id
     */
    private String messageId;

    public String[] getReceivers() {
        return ArrayUtils.clone(receivers);
    }

    public void setReceivers(String[] receivers) {
        this.receivers = ArrayUtils.clone(receivers);
    }
}
