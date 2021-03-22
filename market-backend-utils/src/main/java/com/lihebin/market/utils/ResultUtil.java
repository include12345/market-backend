package com.lihebin.market.utils;


import com.lihebin.market.bean.Code;
import com.lihebin.market.bean.Result;
import com.lihebin.market.enums.CodeEnum;

/**
 * Created by lihebin on 2019/5/22.
 */
public class ResultUtil {

    /**
     * 成功方法
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(Code.CODE_SUCCESS);
        result.setMsg("SUCCESS");
        if (object != null) {
            result.setData(object);
        }
        return result;
    }


    /**
     * 操作成功不返回消息
     * @return
     */
    public static Result success() {
        return success(null);
    }


    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(CodeEnum code) {
        Result result = new Result();
        result.setCode(code.getCode());
        result.setMsg(code.getDesc());
        return result;
    }
}
