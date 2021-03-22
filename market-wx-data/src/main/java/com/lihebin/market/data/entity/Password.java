package com.lihebin.market.data.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by lihebin on 2020/10/25.
 */
@Data
public class Password {

    /**
     * 主键
     */
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String password;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 是否删除 1删除 0未删除
     */
    private Integer status;
}
