package com.lihebin.market.websocket.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.service.ChatRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lihebin on 2020/6/30.
 */
@RestController
@RequestMapping("/record")
@Slf4j
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;


    @RequestMapping(value = "/listChatRecordPaging", method = RequestMethod.GET)
    public Result listChatRecordPaging(
                                             @RequestParam(value = "from", required = false) String from,
                                             @RequestParam(value = "to", required = false) String to,
                                             @RequestParam(value = "ctimeStart", required = false) long ctimeStart,
                                             @RequestParam(value = "ctimeEnd", required = false) long ctimeEnd,
                                             @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "30") int pageSize
    ) {
        return ResultUtil.success(chatRecordService.listChatRecordPage(from, to, ctimeStart, ctimeEnd, pageNo, pageSize));
    }

    @RequestMapping(value = "/listUnReadMessages", method = RequestMethod.GET)
    public Result listUnReadMessages(@RequestHeader("token") String token) {
        return ResultUtil.success(chatRecordService.listUnReadMessages(token));
    }

}
