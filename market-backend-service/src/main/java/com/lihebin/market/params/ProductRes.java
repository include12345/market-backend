package com.lihebin.market.params;


import lombok.Data;

import java.util.Date;

/**
 * 商户产品
 * Created by lihebin on 2019/4/15.
 */
@Data
public class ProductRes {

    private Long id;

    private Long merchantId;

    private String name;

    private Integer priority;

    private Long price;

    private Long count;

    private Long originalPrice;

    private String imageMaster;

    private String image;

    private String context;

    private Long industryId;

    private Integer status;

    private String industryLevel1;

    private String industryLevel2;

    private String industryLevel3;

    private String industryLevel4;

    private Date ctime;

    private Date mtime;




}
