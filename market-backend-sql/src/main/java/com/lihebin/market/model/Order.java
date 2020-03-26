package com.lihebin.market.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 * Created by lihebin on 2019/4/15.
 */
@Entity
@Table(name = "`order`")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

//    @Version
//    private Long version;

    @Column
    private String sn;

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

//    @Column(name = "consumer_id")
//    private String consumerId;

    @Column
    private Integer type;

    @Column
    private Integer status;
}
