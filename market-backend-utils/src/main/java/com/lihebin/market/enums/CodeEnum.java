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

    SUCCESS(200, "成功"),
    FAIL_UN_LOGIN(601, "请登录"),
    FAIL_UN_LOGIN_USER_OR_PASS(602, "用户名或密码错误"),
    KAPTCHA_ERROR(603, "验证码错误"),
    FAIL_UN_LOGIN_CHECKED(604, "用户帐号已锁定不可用"),
    FAIL_UN_LOGIN_NO_AUTH(605, "认证失败"),


    FAIL_UN_AUTHZ(606, "无操作权限"),
    FAIL_PARAMS(607, "参数错误! "),
    FAIL_IO_ERROR(608, "IO异常"),
    FAIL_FILE_NAME_NULL(609, "文件名称不能为空"),
    KEY_NAME_NULL(610, "根据key查不到文件"),
    KAPTCHA_NULL(620, "生成验证码失败"),



    FAIL_SYSTEM_ERROR(999, "系统错误! ");



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
