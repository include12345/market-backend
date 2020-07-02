package com.lihebin.market.websocket.service;

import com.lihebin.market.mongo.entity.ChatRecordEntity;
import com.lihebin.market.websocket.domain.MessageVO;
import org.springframework.data.domain.Page;

/**
 * Created by lihebin on 2020/7/2.
 */
public interface ChatRecordService {


    /**
     * 分页获取消息
     *
     * @param from
     * @param to
     * @param ctimeStart
     * @param ctimeEnd
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<ChatRecordEntity> listChatRecordPage(String from, String to,
                                              long ctimeStart, long ctimeEnd,
                                              int pageNo, int pageSize);


    /**
     * 修改消息状态
     *
     * @param from
     * @param to
     * @param messageId
     */
    void updateChatRecordReadedStatus(String from, String to, String messageId);


    /**
     * 创建消息
     *
     * @param messageVO
     * @return
     */
    ChatRecordEntity createChatRecord(MessageVO messageVO);
}
