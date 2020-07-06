package com.lihebin.market.websocket.service;

import com.lihebin.market.mongo.entity.ChatRecordEntity;
import com.lihebin.market.websocket.domain.MessageVO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

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
     */
    void updateChatRecordReadedStatus(String from, String to);


    /**
     * 创建消息
     *
     * @param messageVO
     * @return
     */
    ChatRecordEntity createChatRecord(MessageVO messageVO);


    Map<String, List<ChatRecordEntity>> listUnReadMessages(String token);
}
