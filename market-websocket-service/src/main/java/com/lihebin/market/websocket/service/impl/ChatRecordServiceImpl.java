package com.lihebin.market.websocket.service.impl;

import com.lihebin.market.mongo.dao.ChatRecordDao;
import com.lihebin.market.mongo.entity.ChatRecordEntity;
import com.lihebin.market.websocket.domain.MessageVO;
import com.lihebin.market.websocket.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2020/7/2.
 */
@Service
public class ChatRecordServiceImpl implements ChatRecordService{

    @Autowired
    private ChatRecordDao chatRecordDao;

    @Override
    public Page<ChatRecordEntity> listChatRecordPage(String from, String to,
                                                     long ctimeStart, long ctimeEnd,
                                                     int pageNo, int pageSize) {

        return chatRecordDao.findByFromOrToPage(from, to, ctimeStart, ctimeEnd,
                PageRequest.of(pageNo, pageSize,
                        Sort.by(Sort.Direction.DESC, "ctime")));
    }

    @Override
    public void updateChatRecordReadedStatus(String from, String to) {
        List<ChatRecordEntity> chatRecordEntityList = chatRecordDao.findAllByFromAndToAndStatus(from, to, ChatRecordEntity.UN_READ);
        chatRecordEntityList.forEach(chatRecordEntity -> chatRecordEntity.setStatus(ChatRecordEntity.READED));
        chatRecordDao.saveAll(chatRecordEntityList);
    }

    @Override
    public ChatRecordEntity createChatRecord(MessageVO messageVO) {
        ChatRecordEntity chatRecordEntity = new ChatRecordEntity();
        chatRecordEntity.setFrom(messageVO.getFrom());
        chatRecordEntity.setTo(messageVO.getTo());
        chatRecordEntity.setMessageId(messageVO.getMessageId());
        chatRecordEntity.setCtime(messageVO.getCtime());
        chatRecordEntity.setStatus(ChatRecordEntity.UN_READ);
        chatRecordEntity = chatRecordDao.save(chatRecordEntity);
        return chatRecordEntity;
    }

    @Override
    public Map<String, List<ChatRecordEntity>> listUnReadMessages(String token) {
        //        String username = userCache.getUsername(token);
        String username = "test";
        List<ChatRecordEntity> chatRecordEntityList = chatRecordDao.findAllByToAndStatus(username, ChatRecordEntity.UN_READ);
        Map<String, List<ChatRecordEntity>> chatRecordEntityListMap = chatRecordEntityList.stream().collect(Collectors.groupingBy(ChatRecordEntity::getFrom));
        chatRecordEntityListMap.forEach(
                (from, subChatRecordEntityList) -> subChatRecordEntityList
                        .stream()
                        .sorted(Comparator.comparingLong(ChatRecordEntity::getCtime).reversed())
                        .collect(Collectors.toList()));

        return chatRecordEntityListMap;
    }
}
