package com.lihebin.market.wx.annotation;

import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.wx.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/26 10:51 下午
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader(JwtHelper.TOKEN);
        if (StringUtil.empty(token)) {
            return null;
        }
        return jwtHelper.getUserIdByToken(token);
    }
}
