package com.lihebin.market.dao;

import com.lihebin.market.model.UserFriendReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihebin on 2019/4/16.
 */
@Repository
public interface UserFriendReqDao extends JpaRepository<UserFriendReq, Long> {



    List<UserFriendReq> findAllByUsername(String username);


    @Modifying
    @Query(nativeQuery = true, value = "delete from user_friend_req where id = ?1")
    void deleteById(long id);



    UserFriendReq findByUsernameAndFriendname(String username, String friendName);
}
