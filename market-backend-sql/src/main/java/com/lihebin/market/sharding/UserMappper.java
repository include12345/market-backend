package com.lihebin.market.sharding;

import com.lihebin.market.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lihebin on 2020/10/25.
 */
@Mapper
public interface UserMappper {

    int insertUsers(List<User> userList);

    List<User> selectAll();
}
