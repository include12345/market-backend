package com.lihebin.market.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lihebin on 2019/4/16.
 */
@Entity
@Table(name = "merchant_user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MerchantUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "ctime")
    private Date ctime;

    @LastModifiedDate
    @Column(name = "mtime")
    private Date mtime;

    @Column
    private Boolean deleted;

    @Column
    private Long version;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Long merchant_id;

    @Column
    private Integer type;

}
