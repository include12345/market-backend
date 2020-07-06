package com.lihebin.market.dao;

import com.lihebin.market.model.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lihebin on 2019/4/16.
 */
@Repository
public interface UserFriendDao extends JpaRepository<UserFriend, Long> {



    @Query(value = "select f.id, f.remark, f.ctime, f.mtime, f.friendname, u.imageUrl, u.nickname " +
            "from user_friend f left join merchant_user u on f.friendname = u.username " +
            "where f.username = :username order by f.friendname", nativeQuery = true)
    List<Map<String, Object>> listFriendsByUsername(@Param("username") String username);


    @Modifying
    @Query(nativeQuery = true, value = "delete from user_friend where id = ?1")
    void deleteById(long id);


    UserFriend findByFriendname(String friendName);


    UserFriend findByUsernameAndFriendname(String username, String friendName);
}
