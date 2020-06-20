package com.lihebin.market.websocket.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by lihebin on 2020/6/19.
 */
@Getter
@Setter
public class Contacts {


    private String tag;

    private List<Map<String, Object>> friendsInfo;
}
