package com.lihebin.market.sharding;

import com.lihebin.market.entity.Password;
import com.lihebin.market.entity.User;

import java.util.List;

/**
 * Created by lihebin on 2020/10/25.
 */
public interface PasswordMapper {

    int insertPassword(List<Password> passwordList);

    Password selectPasswordByUserId();

}
