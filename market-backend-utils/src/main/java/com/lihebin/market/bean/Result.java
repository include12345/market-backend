package com.lihebin.market.bean;

import com.lihebin.market.enums.CodeEnum;

/**
 * Created by lihebin on 2019/5/22.
 */
public class Result<T> {
    //错误码
    private Integer code;

    //信息描述
    private String msg;

    //具体的信息内容
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getDesc();
        this.data = data;
    }
}
