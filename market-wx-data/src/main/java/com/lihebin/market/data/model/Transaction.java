package com.lihebin.market.data.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 流水
 * Created by lihebin on 2019/4/15.
 */
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_create")
    private String operatorCreate;

    @Column(name = "operator_update")
    private String operatorUpdate;

    @Column
    private Boolean deleted = false;

    @Column(name = "order_id")
    private Long orderId;

    @Column
    private String name;

    @Column
    private String remark;

    @Column(name = "original_amount")
    private Long originalAmount;

    @Column(name = "pay_amount")
    private Long payAmount;

    @Column
    private Long discount;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Column
    private Integer type;

    @Column
    private Integer status;

//    @Version
//    private Long version;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

}
