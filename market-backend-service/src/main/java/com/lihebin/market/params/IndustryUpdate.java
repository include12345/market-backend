package com.lihebin.market.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商户产品类型
 * Created by lihebin on 2019/4/15.
 */
@Data
public class IndustryUpdate {

    @NotNull
    private Long id;

    @Length(max = 128, message = "level1长度范围0-128个字符")
    private String level1;

    @Length(max = 128, message = "level2长度范围0-128个字符")
    private String level2;

    @Length(max = 128, message = "level3长度范围0-128个字符")
    private String level3;

    @Length(max = 128, message = "level4长度范围0-128个字符")
    private String level4;

}
