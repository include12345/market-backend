package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.domain.req.CommentReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @description: 用户评论服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/comment")
public class CommentController {



    /**
     * 发表评论
     *
     * @return
     */
    @PostMapping("/post")
    public Result update(@LoginUser Long userId, @RequestBody CommentReq commentReq) {
        return null;
    }

    /**
     * 评论数量
     *
     * @param type
     * @param valueId
     * @return
     */
    @GetMapping("/count")
    public Result count(@NotNull Integer type, @NotNull Long valueId) {
        return null;
    }

    /**
     * 评论列表
     *
     * @param type     类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId  商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param page
     * @param pageSize
     * @param sort
     * @param orderBy
     * @return
     */
    @GetMapping("/list")
    public Result list(
            @NotNull Integer type,
                       @NotNull Long valueId,
                       @NotNull Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "addTime") String sort,
                       @RequestParam(defaultValue = "desc") String orderBy) {
        return null;
    }

    /**
     * 用户收藏添加或删除
     *
     * @return
     */
    @PostMapping("/update")
    public Result update(@LoginUser Long userId, @RequestBody Map<String, String> request) {
        return null;
    }

}
