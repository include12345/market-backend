package com.lihebin.market.websocket.cache;

import com.lihebin.market.websocket.constant.RobotConstant;
import com.lihebin.market.websocket.constant.UserStatus;
import com.lihebin.market.websocket.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihebin on 2020/5/30.
 */
public class UserCache {

    /**
     * 在线用户列表
     */
    private final static ConcurrentHashMap<String, User> USER_MAP = new ConcurrentHashMap<>(32);

    static {
        // 初始化机器人信息
        String uid = RobotConstant.key;
        User user = new User();
        user.setUserId(uid);
        user.setUsername(RobotConstant.name);
        user.setAvatar(RobotConstant.avatar);
        user.setAddress(RobotConstant.address);
        user.setStatus(UserStatus.ONLINE);

        // 将机器人加入到用户列表
        USER_MAP.put(uid, user);
    }

    /**
     * 添加用户
     *
     * @param key 存储的键
     * @param user 存储的user对象
     */
    public static void addUser(String key, User user) {
        if (USER_MAP.containsKey(key)) {
            return;
        }

        USER_MAP.put(key, user);
    }

    /**
     * 获取用户
     *
     * @param key 存储的键
     * @return user对象
     */
    public static User getUser(String key) {
        return USER_MAP.get(key);
    }

    /**
     * 删除用户
     *
     * @param key 存储的键
     */
    public static void removeUser(String key) {
        USER_MAP.remove(key);
    }

    /**
     * 获取在线用户数
     *
     * @return 在线人数
     */
    public static int getOnlineCount() {
        return USER_MAP.size();
    }

    /**
     * 获取所有的在线用户
     *
     * @return 在线人数列表
     */
    public static List<User> listUser() {
        return new ArrayList<>(USER_MAP.values());
    }
}
