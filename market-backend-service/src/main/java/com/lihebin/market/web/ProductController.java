package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
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
@RequestMapping("/api/merchant/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/listMerchantProductPaging", method = RequestMethod.GET)
    public Result listMerchantProductPaging(@RequestHeader("token") String token,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "industryId", required = false) Long industryId,
                                            @RequestParam(value = "status", required = false) Long status,
                                            @RequestParam(value = "ctimeStart", required = false) Date ctimeStart,
                                            @RequestParam(value = "ctimeEnd", required = false) Date ctimeEnd,
                                            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "30") int pageSize
    ) {
        return ResultUtil.success(productService.listProductPaging(token,  Optional.ofNullable(name),
                Optional.ofNullable(industryId), Optional.ofNullable(status),
                Optional.ofNullable(ctimeStart), Optional.ofNullable(ctimeEnd),
                pageNo, pageSize));
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public void logout(@RequestParam(value = "token", required = true) String token) {
//        merchantUserService.logout(token);
//    }

    @RequestMapping(value = "/getMerchantProduct", method = RequestMethod.GET)
    public Result getMerchantProduct(@RequestHeader("token") String token,
                                      @RequestParam(value = "productId", required = true) long productId
    ) {
        return ResultUtil.success(productService.getProduct(token, productId));
    }

    @DeleteMapping(value = "/deleteMerchantProduct/{id:\\d+}")
    public Result deleteMerchantProduct(@RequestHeader("token") String token, @PathVariable long id) {
        productService.deleteProduct(token, id);
        return ResultUtil.success(null);
    }


    @RequestMapping(value = "/addMerchantProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addMerchantProduct(@Valid @RequestHeader("token") String token, @RequestBody ProductAdd productAdd) {
        return ResultUtil.success(productService.addProduct(token, productAdd));
    }

    @RequestMapping(value = "/updateMerchantProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateMerchantProduct(@Valid @RequestHeader("token") String token, @RequestBody ProductUpdate productUpdate) {
        return ResultUtil.success(productService.updateProduct(token, productUpdate));
    }

}
