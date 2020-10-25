package com.lihebin.market.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by lihebin on 2020/10/25.
 */
@Data
public class User {

    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

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
