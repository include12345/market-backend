package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @description: 品牌服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/catalog")
public class CatalogController {


    /**
     * 获取一级目录
     * @return
     */
    @GetMapping("/getfirstcategory")
    public Result getfirstCategory() {
        return null;
    }

    /**
     * 获取二级目录
     *
     * @param id
     * @return
     */
    @GetMapping("/getsecondcategory")
    public Result getSecondCategory(@NotNull Long id) {
        return null;
    }

    /**
     * 分类详情
     * @param id
     * @return
     */
    @GetMapping("/index")
    public Result index(@NotNull Long id) {
        return null;
    }

    /**
     * 所有分类数据
     * @return
     */
    @GetMapping("/all")
    public Result queryAll() {
        return null;
    }

    /**
     * 当前分类栏目
     *
     * @param id
     * @return
     */
    @GetMapping("/current")
    public Result current(@NotNull Long id) {
        return null;
    }

}
