package com.lihebin.market.websocket.annotation;

import java.lang.annotation.*;

/**
 * 聊天记录注解
 * <p>
 * 加上这个注解的特定方法，会将聊天记录信息记录到文件中。
 * 特定方法是指方法必需以MessageVO类或该类的子类作为参数，
 * 如果有多个MessageVO类或该类的子类作为参数，则默认取第一个
 *
 * Created by lihebin on 2020/5/30.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ChatRecord {
}
