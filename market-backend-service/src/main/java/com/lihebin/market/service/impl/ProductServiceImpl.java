package com.lihebin.market.service.impl;

import com.lihebin.market.params.*;
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
    public Page<ConsumerRes> listProductPaging(String token, Optional<String> name,
                                               Optional<Long> industryId, Optional<Long> status,
                                               Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                               int pageNo, int pageSize) {
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

    @Override
    public Page<IndustryRes> listIndustryPaging(Optional<String> level1, Optional<String> level2, Optional<String> level3, Optional<String> level4, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public IndustryRes getIndustry(long industryId) {
        return null;
    }

    @Override
    public void deleteIndustry(long id) {

    }

    @Override
    public IndustryRes addIndustry(IndustryAdd industryAdd) {
        return null;
    }

    @Override
    public IndustryRes updateIndustry(IndustryUpdate industryUpdate) {
        return null;
    }
}
