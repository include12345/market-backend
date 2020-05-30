package com.lihebin.market.exception;

import com.lihebin.market.enums.Code;

/**
 * Created by lihebin on 2018/6/25.
 */
public class BackendException extends RuntimeException {

    private Integer code;

    private String message;


    public BackendException() {

    }


    public BackendException(Code codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
    }

    public BackendException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public BackendException(Integer code, String message, Object... args) {
        this(code, String.format(message, args));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
