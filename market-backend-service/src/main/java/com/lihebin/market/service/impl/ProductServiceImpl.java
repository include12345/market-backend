package com.lihebin.market.service.impl;

import com.lihebin.market.bean.Code;
import com.lihebin.market.dao.IndustryDao;
import com.lihebin.market.dao.MerchantProductDao;
import com.lihebin.market.domain.MerchantDomainService;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.Industry;
import com.lihebin.market.model.MerchantProduct;
import com.lihebin.market.params.*;
import com.lihebin.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2020/3/5.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MerchantDomainService merchantDomainService;

    @Autowired
    private MerchantProductDao merchantProductDao;


    @Autowired
    private IndustryDao industryDao;

    @Override
    public Page<ProductRes> listProductPaging(String token, Optional<String> name,
                                               Optional<Long> industryId, Optional<Long> status,
                                               Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                               int pageNo, int pageSize) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        Page<MerchantProduct> merchantPage = merchantProductDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<Long> merchantIdPath = root.get("merchantId");
            Path<String> namePath = root.get("name");
            Path<Long> industryIdPath = root.get("industryId");
            Path<Long> statusPath = root.get("status");
            Path<Date> timePath = root.get("ctime");
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(merchantIdPath, userMessage.getMerchantId()));
            name.ifPresent(s -> predicateList.add(criteriaBuilder.like(namePath, "%" + s + "%")));
            industryId.ifPresent(s -> predicateList.add(criteriaBuilder.equal(industryIdPath, s)));
            status.ifPresent(s -> predicateList.add(criteriaBuilder.equal(statusPath, s)));
            if (ctimeStart.isPresent() && ctimeEnd.isPresent()) {
                predicateList.add(criteriaBuilder.between(timePath, ctimeStart.get(), ctimeEnd.get()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "ctime")));
        Map<Long, Industry> industryMap = listIndustrys().stream()
                .collect(Collectors.toMap(Industry::getId, industry -> industry));
        return new PageImpl<>(
                merchantPage.getContent()
                        .stream()
                        .map(product -> buildProductRes(product, industryMap))
                        .collect(Collectors.toList()),
                merchantPage.previousPageable(), merchantPage.getTotalElements());
    }

    @Override
    public void deleteProduct(String token, long id) {
        checkMerchantProduct(token, id);
        merchantProductDao.deleteById(id);
    }

    @Override
    public ProductRes getProduct(String token, long id) {
        MerchantProduct merchantProduct = checkMerchantProduct(token, id);
        Map<Long, Industry> industryMap = listIndustrys().stream()
                .collect(Collectors.toMap(Industry::getId, industry -> industry));
        return buildProductRes(merchantProduct, industryMap);
    }

    @Override
    public ProductRes addProduct(String token, ProductAdd productAdd) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        MerchantProduct merchantProduct = merchantProductDao.findByMerchantIdAndName(merchantId, productAdd.getName());
        if (null != merchantProduct) {
            throw new BackendException(Code.CODE_EXIST, "商户商品名称[%s]已存在", productAdd.getName());
        }
        checkIndustry(productAdd.getIndustryId());
        merchantProduct = new MerchantProduct();
        merchantProduct.setMerchantId(merchantId);
        merchantProduct.setName(productAdd.getName());
        merchantProduct.setPriority(productAdd.getPriority());
        merchantProduct.setIndustryId(productAdd.getIndustryId());
        merchantProduct.setOriginalPrice(productAdd.getOriginalPrice());
        merchantProduct.setPrice(productAdd.getPrice());
        merchantProduct.setImage(productAdd.getImage());
        merchantProduct.setCount(productAdd.getCount());
        merchantProduct.setContext(productAdd.getContext());
        merchantProduct.setStatus(0);
        merchantProduct.setOperatorCreate(userMessage.getUsername());
        merchantProduct.setOperatorUpdate(userMessage.getUsername());
        merchantProduct = merchantProductDao.save(merchantProduct);
        ProductRes productRes = new ProductRes();
        productRes.setId(merchantProduct.getId());
        return productRes;
    }



    @Override
    public ProductRes updateProduct(String token, ProductUpdate productUpdate) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        MerchantProduct merchantProduct = checkMerchantProduct(token, productUpdate.getId());
        MerchantProduct merchantProductByName = merchantProductDao.findByMerchantIdAndName(merchantId, productUpdate.getName());
        if (merchantProductByName != null && !merchantProductByName.getId().equals(productUpdate.getId())) {
            throw new BackendException(Code.CODE_EXIST, "商户商品名称[%s]已存在", merchantProductByName.getName());
        }

        merchantProduct.setName(productUpdate.getName());
        merchantProduct.setPriority(productUpdate.getPriority());
        merchantProduct.setIndustryId(productUpdate.getIndustryId());
        merchantProduct.setOriginalPrice(productUpdate.getOriginalPrice());
        merchantProduct.setPrice(productUpdate.getPrice());
        merchantProduct.setImage(productUpdate.getImage());
        merchantProduct.setCount(productUpdate.getCount());
        merchantProduct.setStatus(productUpdate.getStatus());
        merchantProduct.setContext(productUpdate.getContext());
        merchantProduct.setOperatorUpdate(userMessage.getUsername());
        merchantProductDao.save(merchantProduct);
        ProductRes productRes = new ProductRes();
        productRes.setId(merchantProduct.getId());
        return productRes;
    }

    @Override
    public Page<Industry> listIndustryPaging(Optional<String> level1, Optional<String> level2,
                                                Optional<String> level3, Optional<String> level4,
                                                int pageNo, int pageSize) {
        Page<Industry> industryPage = industryDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> level1Path = root.get("level1");
            Path<String> level2Path = root.get("level2");
            Path<String> level3Path = root.get("level3");
            Path<String> level4Path = root.get("level4");
            List<Predicate> predicateList = new ArrayList<>();
            level1.ifPresent(s -> predicateList.add(criteriaBuilder.like(level1Path, "%" + s + "%")));
            level2.ifPresent(s -> predicateList.add(criteriaBuilder.like(level2Path, "%" + s + "%")));
            level3.ifPresent(s -> predicateList.add(criteriaBuilder.like(level3Path, "%" + s + "%")));
            level4.ifPresent(s -> predicateList.add(criteriaBuilder.like(level4Path, "%" + s + "%")));

            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        return new PageImpl<>(
                new ArrayList<>(industryPage.getContent()),
                industryPage.previousPageable(), industryPage.getTotalElements());
    }

    @Override
    public Industry getIndustry(long industryId) {
        return checkIndustry(industryId);
    }

    @Override
    public List<Industry> listIndustrys() {
        return industryDao.findAll();
    }

    @Override
    public void deleteIndustry(long id) {
        checkIndustry(id);
        industryDao.deleteById(id);
    }

    @Override
    public Industry addIndustry(IndustryAdd industryAdd) {
        Industry industry = new Industry();
        industry.setLevel1(industryAdd.getLevel1());
        industry.setLevel2(industryAdd.getLevel2());
        industry.setLevel3(industryAdd.getLevel3());
        industry.setLevel4(industryAdd.getLevel4());
        industry.setDepth(4);
        return industryDao.save(industry);
    }

    @Override
    public Industry updateIndustry(IndustryUpdate industryUpdate) {
        Industry industry = checkIndustry(industryUpdate.getId());
        industry.setLevel1(industryUpdate.getLevel1());
        industry.setLevel2(industryUpdate.getLevel2());
        industry.setLevel3(industryUpdate.getLevel3());
        industry.setLevel4(industryUpdate.getLevel4());
        industry.setDepth(4);
        return industryDao.save(industry);
    }


    private Industry checkIndustry(Long industryId) {
        Optional<Industry> industry = industryDao.findById(industryId);
        if (!industry.isPresent()) {
            throw new BackendException(Code.CODE_NOT_EXIST, "商品类型不存在");
        }
        return industry.get();
    }

    /**
     * 校验token商户商品
     *
     * @param token
     * @param id
     * @return
     */
    private MerchantProduct checkMerchantProduct(String token, long id) {
        long merchantId = merchantDomainService.getUserMessage(token).getMerchantId();
        Optional<MerchantProduct> merchantProduct = merchantProductDao.findById(id);
        if (!merchantProduct.isPresent() || !merchantProduct.get().getMerchantId().equals(merchantId)) {
            throw new BackendException(Code.CODE_PARAM_ERROR, "账户无权限操作会员");
        }
        return merchantProduct.get();
    }
}
