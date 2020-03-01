package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.params.ConsumerAdd;
import com.lihebin.market.params.ConsumerUpdate;
import com.lihebin.market.service.ConsumerService;
import com.lihebin.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2019/5/22.
 */
@RestController
@RequestMapping("/api/merchant")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    @RequestMapping(value = "/listMerchantConsumerPaging", method = RequestMethod.GET)
    public Result listMerchantConsumerPaging(@RequestHeader("token") String token,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "ctimeStart", required = false) Date ctimeStart,
                                             @RequestParam(value = "ctimeEnd", required = false) Date ctimeEnd,
                                             @RequestParam(value = "cellphone", required = false) String cellphone,
                                             @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "30") int pageSize
    ) {
        return ResultUtil.success(consumerService.listMerchantConsumerPaging(token, Optional.ofNullable(ctimeStart), Optional.ofNullable(ctimeEnd),
                Optional.ofNullable(name), Optional.ofNullable(cellphone),
                pageNo, pageSize));
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public void logout(@RequestParam(value = "token", required = true) String token) {
//        merchantUserService.logout(token);
//    }

    @RequestMapping(value = "/getMerchantConsumer", method = RequestMethod.GET)
    public Result getMerchantConsumer(@RequestHeader("token") String token,
                                             @RequestParam(value = "consumerId", required = true) Long consumerId
    ) {
        return ResultUtil.success(consumerService.getMerchantConsumer(token, consumerId));
    }

    @DeleteMapping(value = "/deleteMerchantConsumer/{id:\\d+}")
    public Result deleteMerchantConsumer(@RequestHeader("token") String token, @PathVariable long id) {
        consumerService.deleteMerchantConsumer(token, id);
        return ResultUtil.success(null);
    }


    @RequestMapping(value = "/addMerchantConsumer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addMerchantConsumer(@Valid @RequestHeader("token") String token, @RequestBody ConsumerAdd consumerAdd) {
        return ResultUtil.success(consumerService.addMerchantConsumer(token, consumerAdd));
    }

    @RequestMapping(value = "/updateMerchantConsumer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateMerchantConsumer(@Valid @RequestHeader("token") String token, @RequestBody ConsumerUpdate consumerUpdate) {
        return ResultUtil.success(consumerService.updateMerchantConsumer(token, consumerUpdate));
    }


}
