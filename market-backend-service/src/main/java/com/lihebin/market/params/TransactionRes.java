package com.lihebin.market.params;


import lombok.Data;

import java.util.Date;

/**
 * 商户流水
 * Created by lihebin on 2019/4/15.
 */
@Data
public class TransactionRes {


    private Long id;

    private Long orderId;

    private String orderSn;

    private String name;

    private String remark;

    private Long originalAmount;

    private Long payAmount;

    private Long discount;

    private Long merchantId;

    private String merchantName;

    private Integer type;

    private Integer status;

    private String operatorCreate;

    private String operatorUpdate;

    private Date ctime;

    private Date mtime;

}
