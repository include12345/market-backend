package com.lihebin.market.model;

import lombok.Data;
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
@Table(name = "industry")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Industry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String level1;

    @Column
    private String level2;

    @Column
    private String level3;

    @Column
    private String level4;

    @Column
    private Integer depth;


}
