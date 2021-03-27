package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 商品服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/goods")
public class GoodsController {



    /**
     * 根据条件搜素商品
     * <p>
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(@LoginUser Long userId,
                       Long categoryId,
                       Long brandId,
                       String keyword,
                       Boolean isNew,
                       Boolean isHot,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }


    /**
     * 商品详情
     *
     * @return
     */
    @GetMapping("/detail")
    public Result detail(@LoginUser Long userId,
                         @NotNull Long id) {
        return null;
    }

    /**
     * 商品分类类目
     * @param id
     * @return
     */
    @GetMapping("/category")
    public Result category(@NotNull Long id) {
        return null;
    }

    /**
     * 商品详情页面“大家都在看”推荐商品
     *
     * @param id
     * @return
     */
    @GetMapping("/related")
    public Result related(@NotNull Long id) {
        return null;
    }

    /**
     * 在售的商品总数
     *
     * @return
     */
    @GetMapping("/count")
    public Result count() {
        return null;
    }

}
