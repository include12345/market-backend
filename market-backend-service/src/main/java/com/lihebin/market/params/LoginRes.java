package com.lihebin.market.params;


/**
 * Created by lihebin on 2019/4/16.
 */
public class LoginRes {

    private Long merchantId;

    private String token;

    private Integer type;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
