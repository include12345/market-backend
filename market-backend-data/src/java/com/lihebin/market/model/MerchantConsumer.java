package com.lihebin.market.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lihebin on 2020/2/27.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "merchant_consumer")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MerchantConsumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String cellphone;

    @Column
    private String email;

    @Column
    private String wechat;

    @Column(name = "merchant_id")
    private Long merchantId;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column(name = "operator_create")
    private String operatorCreate;

    @Column(name = "operator_update")
    private String operatorUpdate;

    @Column
    private Boolean deleted = false;

    @Version
    private Long version;
}
