package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by lihebin on 2020/6/20.
 */
@Getter
@Setter
public class FriendUpdate {


    @NotNull
    private String friendName;

    @NotBlank
    private String remark;
}
