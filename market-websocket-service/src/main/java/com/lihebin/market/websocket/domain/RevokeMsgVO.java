package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by lihebin on 2020/5/30.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RevokeMsgVO extends MessageVO {

    /**
     * 撤回的消息id
     */
    private String revokeMessageId;
}
