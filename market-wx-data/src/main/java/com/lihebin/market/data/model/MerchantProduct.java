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
 * Created by lihebin on 2020/2/27.
 */
@Table(name = "merchant_product")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MerchantProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer priority;

    @Column
    private Long price;

    @Column(name = "original_price")
    private Long originalPrice;

    @Column
    private Long count;

    @Column
    private String image;

    @Column
    private String context;

    @Column
    private Integer status;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "industry_id")
    private Long industryId;

    @Column(name = "operator_create")
    private String operatorCreate;

    @Column(name = "operator_update")
    private String operatorUpdate;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted = false;
}
