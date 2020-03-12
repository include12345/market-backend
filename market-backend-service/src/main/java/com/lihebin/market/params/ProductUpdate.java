package com.lihebin.market.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 商户产品
 * Created by lihebin on 2019/4/15.
 */
@Data
public class ProductUpdate {

    @NotNull
    private Long id;

    @NotBlank
    @Length(max = 128, message = "会员名称长度范围0-128个字符")
    private String name;

    @NotBlank(message = "商品优先级不能为空")
    private Integer priority;

    @NotBlank(message = "商品状态不能为空")
    private Integer status;

    @NotBlank(message = "商品价格不能为空")
    private Long price;


    @NotBlank(message = "商品数量不能为空")
    private Long count;

    @NotBlank(message = "商品原价不能为空")
    private Long originalPrice;

    @NotBlank
    @Length(max = 128, message = "图片地址长度范围0-128个字符")
    private String image;

    private String context;

    @NotBlank(message = "商品类型不能为空")
    private Long industryId;

}
