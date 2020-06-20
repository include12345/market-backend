package com.lihebin.market.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.lihebin.market.bean.Code;
import com.lihebin.market.cache.RedisDao;
import com.lihebin.market.cache.UserCache;
import com.lihebin.market.dao.MerchantUserDao;
import com.lihebin.market.dao.UserFriendDao;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.MerchantUser;
import com.lihebin.market.model.UserFriend;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.constant.StompConstant;
import com.lihebin.market.websocket.domain.Contacts;
import com.lihebin.market.websocket.domain.FriendAdd;
import com.lihebin.market.websocket.domain.FriendUpdate;
import com.lihebin.market.websocket.domain.MessageVO;
import com.lihebin.market.websocket.enums.MessageTypeEnum;
import com.lihebin.market.websocket.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2020/6/18.
 */
@Service
@Slf4j
public class FriendServiceImpl implements FriendService{

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserFriendDao userFriendDao;

    @Autowired
    private MerchantUserDao merchantUserDao;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<Contacts> listFriendsByToken(String token) {
//        String username = userCache.getUsername(token);

        List<Map<String, Object>> friendList = userFriendDao.listFriendsByUsername("test");
        Map<String, List<Map<String, Object>>> friendsMap = friendList.stream()
                .collect(Collectors.groupingBy((Map m) -> ((String) m.get("friendname")).substring(0, 1)));
        List<Contacts> contactsList = new ArrayList<>();
        friendsMap.forEach((tag, list) -> {
            Contacts contacts = new Contacts();
            contacts.setTag(tag);
            contacts.setFriendsInfo(list);
            contactsList.add(contacts);
        });
        return contactsList.stream()
                .sorted(Comparator.comparing(Contacts::getTag))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean deleteFriend(String token, String friendName) {
        String username = userCache.getUsername(token);
        UserFriend userFriend = userFriendDao.findByUsernameAndFriendname(username, friendName);
        if (userFriend == null) {
            return false;
        }
        userFriendDao.deleteById(userFriend.getId());
        UserFriend friendUserFriend = userFriendDao.findByUsernameAndFriendname(friendName, username);
        if (friendUserFriend != null) {
            userFriendDao.deleteById(friendUserFriend.getId());
        }
        simpMessagingTemplate.convertAndSendToUser(friendName, StompConstant.SUB_USER,
                ResultUtil.success(new MessageVO(username,
                        "删除好友",
                        MessageTypeEnum.DELETE_FRIEND)));
        return true;
    }

    @Override
    public boolean addFriendRequest(String token, FriendAdd friendAdd) {
        String username = userCache.getUsername(token);
        UserFriend userFriend = userFriendDao.findByUsernameAndFriendname(username, friendAdd.getFriendName());
        if (userFriend != null) {
            return false;
        }
        MerchantUser merchantUser = merchantUserDao.findMerchantUserByUsername(friendAdd.getFriendName());
        if (merchantUser == null) {
            return false;
        }
        String uuid = UUID.randomUUID().toString();
        String value = String.format("%s|||%s", username, uuid);
        String key = String.format("%s-%s", username, friendAdd.getFriendName());
        if (redisDao.getValue(key) != null) {
            redisDao.removeValue(key);
        }
        redisDao.cacheValue(key, value, 30, TimeUnit.DAYS);
        simpMessagingTemplate.convertAndSendToUser(friendAdd.getFriendName(), StompConstant.SUB_USER,
                ResultUtil.success(new MessageVO(username,
                        uuid,
                        MessageTypeEnum.ADD_FRIEND)));
        return true;
    }

    @Transactional
    @Override
    public UserFriend addFriend(String token, FriendAdd friendAdd) {
        UserFriend userFriendResult = new UserFriend();
        String username = userCache.getUsername(token);
        if (friendAdd.getKey() == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "添加好友失败");
        }
        String key = String.format("%s-%s", friendAdd.getFriendName(), username);
        String value = redisDao.getValue(key);
        if (value == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "添加好友失败");
        }
        String[] caches = value.split("|||");
        if (!friendAdd.getFriendName().equals(caches[0])
                || !friendAdd.getKey().equals(caches[1])) {
            throw new BackendException(Code.CODE_NOT_EXIST, "添加好友失败");
        }

        if (friendAdd.getIsAgree() == null) {
            throw new BackendException(Code.CODE_NOT_EXIST, "添加好友失败");
        }

        if (friendAdd.getIsAgree()) {
            UserFriend userFriend = userFriendDao.findByUsernameAndFriendname(username, friendAdd.getFriendName());
            if (userFriend != null) {
                throw new BackendException(Code.CODE_NOT_EXIST, "添加好友失败");
            }
            UserFriend userFriendAdd = new UserFriend();
            userFriendAdd.setUsername(username);
            userFriendAdd.setFriendname(friendAdd.getFriendName());
            userFriendAdd.setRemark(friendAdd.getRemark());
            userFriendResult = userFriendDao.save(userFriendAdd);
            UserFriend friendUserFriendAdd = new UserFriend();
            friendUserFriendAdd.setUsername(friendAdd.getFriendName());
            friendUserFriendAdd.setFriendname(username);
            friendUserFriendAdd = userFriendDao.save(friendUserFriendAdd);
            simpMessagingTemplate.convertAndSendToUser(friendAdd.getFriendName(), StompConstant.SUB_USER,
                    ResultUtil.success(new MessageVO(username,
                            JSON.toJSONString(friendUserFriendAdd),
                            MessageTypeEnum.DEAL_ADD_FRIEND_REQ)));
        }
        redisDao.removeValue(key);
        return userFriendResult;
    }

    @Override
    public boolean updateFriend(String token, FriendUpdate friendUpdate) {
        String username = userCache.getUsername(token);
        Optional<UserFriend> userFriendOptional = userFriendDao.findById(friendUpdate.getId());
        if (userFriendOptional.isPresent()
                && userFriendOptional.get().getUsername().equals(username)) {
            UserFriend userFriendOld = userFriendOptional.get();
            userFriendOld.setRemark(friendUpdate.getRemark());
            userFriendDao.save(userFriendOld);
            return true;
        }
        return false;
    }
}