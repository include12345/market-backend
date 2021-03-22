package com.lihebin.market.wx.config;

import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/18 10:07 下午
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ShiroExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Object unauthenticatedHandler(AuthenticationException e) {
        log.warn(e.getMessage(), e);
        return ResultUtil.error(CodeEnum.FAIL_UN_LOGIN);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Object unauthorizedHandler(AuthorizationException e) {
        log.warn(e.getMessage(), e);
        return ResultUtil.error(CodeEnum.FAIL_UN_AUTHZ);
    }
}
