package com.lihebin.market.exception;

import com.lihebin.market.enums.Code;
import com.lihebin.market.enums.CodeEnum;

/**
 * Created by lihebin on 2018/6/25.
 */
public class BackendException extends RuntimeException {

    private int code;

    private String message;


    public BackendException() {

    }


    public BackendException(Code codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
    }

    public BackendException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public BackendException(int code, String message, Object... args) {
        this(code, String.format(message, args));
    }

    public int getCode() {
        return code;
    }



    public void setCode(int code) {
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
