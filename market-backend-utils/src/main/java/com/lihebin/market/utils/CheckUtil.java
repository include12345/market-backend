package com.lihebin.market.utils;

import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lihebin on 2020/5/30.
 */
public class CheckUtil {

    /**
     * 撤消消息过期时间 3分钟
     */
    private static final long MESSAGE_EXPIRE_DATE = 180000;

    /**
     * 校验撤消的消息id
     *
     * @param messageId 消息id
     * @throws BackendException
     */
    public static void checkMessageId(String messageId, String userId) {
        if (StringUtils.isEmpty(messageId)) {
            throw new BackendException(CodeEnum.INVALID_PARAMETERS);
        }

        String[] str = StringUtils.split(messageId, ':');

        if (!userId.equals(str[0])) {
            throw new BackendException(CodeEnum.INVALID_TOKEN);
        }

        // 判断消息是否过期
        if (System.currentTimeMillis() > Long.parseLong(str[1]) + MESSAGE_EXPIRE_DATE) {
            throw new BackendException(CodeEnum.MESSAGE_HAS_EXPIRED);
        }
    }
}
