package com.lihebin.market.websocket.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.websocket.service.ChatRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
