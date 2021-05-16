package com.lihebin.market.wx.domain.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * Created by lihebin on 2021/4/14.
 */
@Setter
@Getter
public class AuthInfoResult {

    private String name;

    private String avatar;

    private Set<String> roles;

    private Set<String> params;

}
