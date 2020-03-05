package com.lihebin.market.service.impl;

import com.lihebin.market.params.ConsumerRes;
import com.lihebin.market.params.ProductAdd;
import com.lihebin.market.params.ProductRes;
import com.lihebin.market.params.ProductUpdate;
import com.lihebin.market.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/5.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Page<ConsumerRes> listProductPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd, Optional<String> name, Optional<String> cellphone, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void deleteProduct(String token, long id) {

    }

    @Override
    public ProductRes getProduct(String token, long id) {
        return null;
    }

    @Override
    public ProductRes addProduct(String token, ProductAdd productAdd) {
        return null;
    }

    @Override
    public ProductRes updateProduct(String token, ProductUpdate productUpdate) {
        return null;
    }
}
