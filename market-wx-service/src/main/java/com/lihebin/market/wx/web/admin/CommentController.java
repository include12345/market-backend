package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description: 评论模块
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/23 9:16 下午
 */
@RestController
@RequestMapping("/admin/comment")
public class CommentController {

    @RequiresPermissions("admin:comment:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "评论管理"}, button = "查询")
    @GetMapping("/list")
    public Result list(Long userId, Long goodsId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:comment:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "评论管理"}, button = "删除")
    @DeleteMapping("/delete")
    public Result delete(@NotNull Long id) {
        //todo
        return null;
    }
}
