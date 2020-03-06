package com.lihebin.market.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 商户产品类型
 * Created by lihebin on 2019/4/15.
 */
@Data
public class IndustryAdd {

    @NotBlank
    @Length(max = 128, message = "level1长度范围0-128个字符")
    private String level1;

    @NotBlank
    @Length(max = 128, message = "level2长度范围0-128个字符")
    private String level2;

    @NotBlank
    @Length(max = 128, message = "level3长度范围0-128个字符")
    private String level3;

    @NotBlank
    @Length(max = 128, message = "level4长度范围0-128个字符")
    private String level4;

}
