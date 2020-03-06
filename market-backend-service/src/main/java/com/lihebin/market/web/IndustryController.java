package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.params.IndustryAdd;
import com.lihebin.market.params.IndustryUpdate;
import com.lihebin.market.params.ProductAdd;
import com.lihebin.market.params.ProductUpdate;
import com.lihebin.market.service.ProductService;
import com.lihebin.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/1.
 */
@RestController
@RequestMapping("/api/industry")
public class IndustryController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/listIndustryPaging", method = RequestMethod.GET)
    public Result listIndustryPaging(@RequestParam("level1") String level1,
                                            @RequestParam(value = "level2", required = false) String level2,
                                            @RequestParam(value = "level3", required = false) String level3,
                                            @RequestParam(value = "level4", required = false) String level4,
                                            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "30") int pageSize
    ) {
        return ResultUtil.success(productService.listIndustryPaging(Optional.ofNullable(level1),
                Optional.ofNullable(level2), Optional.ofNullable(level3),
                Optional.ofNullable(level4),
                pageNo, pageSize));
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public void logout(@RequestParam(value = "token", required = true) String token) {
//        merchantUserService.logout(token);
//    }

    @RequestMapping(value = "/getIndustry", method = RequestMethod.GET)
    public Result getIndustry(@RequestParam(value = "industryId", required = true) long industryId
    ) {
        return ResultUtil.success(productService.getIndustry(industryId));
    }

    @DeleteMapping(value = "/deleteIndustry/{id:\\d+}")
    public Result deleteIndustry(@PathVariable long id) {
        productService.deleteIndustry(id);
        return ResultUtil.success(null);
    }


    @RequestMapping(value = "/addIndustry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addIndustry(@RequestBody IndustryAdd industryAdd) {
        return ResultUtil.success(productService.addIndustry(industryAdd));
    }

    @RequestMapping(value = "/updateIndustry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateIndustry(@RequestBody IndustryUpdate industryUpdate) {
        return ResultUtil.success(productService.updateIndustry(industryUpdate));
    }

}
