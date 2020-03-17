package com.lihebin.market.service;

import com.lihebin.market.model.Industry;
import com.lihebin.market.model.MerchantProduct;
import com.lihebin.market.params.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lihebin on 2020/3/1.
 */
public interface ProductService {


    /**
     * 获取商户下产品信息
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<ProductRes> listProductPaging(String token, Optional<String> name,
                                        Optional<Long> industryId, Optional<Long> status,
                                        Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                        int pageNo, int pageSize);



    /**
     * 删除商户产品
     *
     * @param id
     */
    void deleteProduct(String token, long id);

    /**
     * 获取商户产品
     *
     * @param token
     * @param id
     * @return
     */
    ProductRes getProduct(String token, long id);

    /**
     * 新增商户产品
     *
     * @param token
     * @param productAdd
     * @return
     */
    ProductRes addProduct(String token, ProductAdd productAdd);

    /**
     * 编辑商户产品
     *
     * @param token
     * @param productUpdate
     * @return
     */
    ProductRes updateProduct(String token, ProductUpdate productUpdate);




    Page<Industry> listIndustryPaging(Optional<String> level1, Optional<String> level2,
                                         Optional<String> level3, Optional<String> level4,
                                         int pageNo, int pageSize);


    Industry getIndustry(long industryId);

    /**
     * 获取全部商品类型
     * @return
     */
    List<Industry> listIndustrys();


    void deleteIndustry(long id);

    Industry addIndustry(IndustryAdd industryAdd);

    Industry updateIndustry(IndustryUpdate industryUpdate);


    /**
     * build ProductRes
     *
     * @param merchantProduct
     * @return
     */
    default ProductRes buildProductRes(MerchantProduct merchantProduct, Map<Long, Industry> industryMap) {
        ProductRes productRes = new ProductRes();
        productRes.setId(merchantProduct.getId());
        productRes.setMerchantId(merchantProduct.getMerchantId());
        productRes.setIndustryId(merchantProduct.getIndustryId());
        productRes.setCount(merchantProduct.getCount());
        productRes.setName(merchantProduct.getName());
        productRes.setImage(merchantProduct.getImage());
        if (merchantProduct.getImage() != null) {
            String[] images = merchantProduct.getImage().split(",");
            productRes.setImageMaster(images[0]);
        }
        productRes.setContext(merchantProduct.getContext());
        productRes.setPrice(merchantProduct.getPrice());
        productRes.setOriginalPrice(merchantProduct.getOriginalPrice());
        productRes.setPriority(merchantProduct.getPriority());
        productRes.setStatus(merchantProduct.getStatus());
        if (industryMap.containsKey(merchantProduct.getIndustryId())) {
            Industry industry = industryMap.get(merchantProduct.getIndustryId());
            productRes.setIndustryLevel1(industry.getLevel1());
            productRes.setIndustryLevel2(industry.getLevel2());
            productRes.setIndustryLevel3(industry.getLevel3());
            productRes.setIndustryLevel4(industry.getLevel4());
        }
        productRes.setCtime(merchantProduct.getCtime());
        productRes.setMtime(merchantProduct.getMtime());
        return productRes;
    }
}
