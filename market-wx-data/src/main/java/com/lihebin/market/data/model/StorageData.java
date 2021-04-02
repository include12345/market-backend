package com.lihebin.market.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: 对象存储
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/25 9:38 下午
 */
@Table(name = "storage")
@EntityListeners(AuditingEntityListener.class)
@Data
public class StorageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文件的唯一索引
     */
    @Column
    private String key;

    /**
     * 文件名
     */
    @Column
    private String name;

    /**
     * 文件类型
     */
    @Column
    private String type;

    /**
     * 文件大小
     */
    @Column
    private Long size;

    /**
     * 文件访问链接
     */
    @Column
    private String url;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted = false;

}
