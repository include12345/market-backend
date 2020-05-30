package com.lihebin.market.enums;

/**
 * Created by lihebin on 2020/5/30.
 */
public enum CodeEnum implements Code {

    UPLOADED_FILE_IS_NOT_AN_IMAGE(1002, "上传的文件不是图片!"),

    MESSAGE_HAS_EXPIRED(1001, "消息已过期，不能撤回！"),

    INTERNAL_SERVER_ERROR(500, "网络异常! "),

    INVALID_PARAMETERS(501, "非法参数! "),

    INVALID_TOKEN(502, "没有权限! "),

    FAILED(503, "处理失败! "),

    SUCCESS(200, "成功");

    private int code;
    private String desc;


    CodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}