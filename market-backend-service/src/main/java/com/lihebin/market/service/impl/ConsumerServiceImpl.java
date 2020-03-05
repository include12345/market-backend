package com.lihebin.market.service.impl;

import com.lihebin.market.bean.Code;
import com.lihebin.market.dao.MerchantConsumerDao;
import com.lihebin.market.domain.ConsumerDomainService;
import com.lihebin.market.domain.MerchantDomainService;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.MerchantConsumer;
import com.lihebin.market.params.ConsumerAdd;
import com.lihebin.market.params.ConsumerRes;
import com.lihebin.market.params.ConsumerUpdate;
import com.lihebin.market.params.UserMessage;
import com.lihebin.market.service.ConsumerService;
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
 * Created by lihebin on 2019/4/29.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDomainService consumerDomainService;

    @Autowired
    private MerchantDomainService merchantDomainService;

    @Autowired
    private MerchantConsumerDao merchantConsumerDao;


    @Override
    public Page<ConsumerRes> listMerchantConsumerPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                                        Optional<String> name, Optional<String> cellphone,
                                                        int pageNo, int pageSize) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        Page<MerchantConsumer> merchantPage = merchantConsumerDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<Long> merchantIdPath = root.get("merchantId");
            Path<String> namePath = root.get("name");
            Path<String> cellphonePath = root.get("cellphone");
            Path<Date> timePath = root.get("ctime");
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(merchantIdPath, userMessage.getMerchantId()));
            name.ifPresent(s -> predicateList.add(criteriaBuilder.like(namePath, "%" + s + "%")));
            cellphone.ifPresent(s -> predicateList.add(criteriaBuilder.equal(cellphonePath, s)));
            if (ctimeStart.isPresent() && ctimeEnd.isPresent()) {
                predicateList.add(criteriaBuilder.between(timePath, ctimeStart.get(), ctimeEnd.get()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "ctime")));
        return new PageImpl<>(
                merchantPage.getContent()
                        .stream()
                        .map(this::buildMerchantConsumerRes)
                        .collect(Collectors.toList()),
                merchantPage.previousPageable(), merchantPage.getTotalElements());
    }

    @Override
    public void deleteMerchantConsumer(String token, long id) {
        checkMerchantConsumer(token, id);
        merchantConsumerDao.deleteById(id);
        consumerDomainService.removeMerchantConsumerCache(id);
    }

    @Override
    public ConsumerRes getMerchantConsumer(String token, long consumerId) {
        MerchantConsumer merchantConsumer = checkMerchantConsumer(token, consumerId);
        return buildMerchantConsumerRes(merchantConsumer);
    }

    @Override
    public ConsumerRes addMerchantConsumer(String token, ConsumerAdd consumerAdd) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        MerchantConsumer merchantConsumer = merchantConsumerDao.findByMerchantIdAndName(merchantId, consumerAdd.getName());
        if (null != merchantConsumer) {
            throw new BackendException(Code.CODE_EXIST, "商户会员名称[%s]已存在", consumerAdd.getName());
        }
        merchantConsumer = merchantConsumerDao.findByMerchantIdAndCellphone(merchantId, consumerAdd.getCellphone());
        if (null != merchantConsumer) {
            throw new BackendException(Code.CODE_EXIST, "商户会员手机号[%s]已存在", consumerAdd.getCellphone());
        }
        merchantConsumer = new MerchantConsumer();
        merchantConsumer.setMerchantId(merchantId);
        merchantConsumer.setName(consumerAdd.getName());
        merchantConsumer.setCellphone(consumerAdd.getCellphone());
        merchantConsumer.setEmail(consumerAdd.getEmail());
        merchantConsumer.setWechat(consumerAdd.getWechat());
        merchantConsumer.setOperatorCreate(userMessage.getUsername());
        merchantConsumer.setOperatorUpdate(userMessage.getUsername());
        merchantConsumer = merchantConsumerDao.save(merchantConsumer);
        ConsumerRes consumerRes = new ConsumerRes();
        consumerRes.setId(merchantConsumer.getId());
        return consumerRes;
    }

    @Override
    public ConsumerRes updateMerchantConsumer(String token, ConsumerUpdate consumerUpdate) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        MerchantConsumer merchantConsumerOld = consumerDomainService.getMerchantConsumer(consumerUpdate.getId());
        if (!merchantConsumerOld.getMerchantId().equals(merchantId)) {
            throw new BackendException(Code.CODE_NOT_EXIST, "商户会员不存在");
        }
        if (!merchantConsumerOld.getName().equals(consumerUpdate.getName())) {
            MerchantConsumer merchantConsumerCheck = merchantConsumerDao.findByMerchantIdAndName(merchantId, consumerUpdate.getName());
            if (null != merchantConsumerCheck) {
                throw new BackendException(Code.CODE_EXIST, "商户会员名称[%s]已存在", consumerUpdate.getName());
            }
        }
        if (!merchantConsumerOld.getCellphone().equals(consumerUpdate.getCellphone())) {
            MerchantConsumer merchantConsumerCheck = merchantConsumerDao.findByMerchantIdAndCellphone(merchantId, consumerUpdate.getCellphone());
            if (null != merchantConsumerCheck) {
                throw new BackendException(Code.CODE_EXIST, "商户会员手机号[%s]已存在", consumerUpdate.getCellphone());
            }
        }
        merchantConsumerOld.setId(consumerUpdate.getId());
        merchantConsumerOld.setName(consumerUpdate.getName());
        merchantConsumerOld.setCellphone(consumerUpdate.getCellphone());
        merchantConsumerOld.setEmail(consumerUpdate.getEmail());
        merchantConsumerOld.setWechat(consumerUpdate.getWechat());
        merchantConsumerOld.setOperatorUpdate(userMessage.getUsername());
        merchantConsumerDao.save(merchantConsumerOld);
        consumerDomainService.removeMerchantConsumerCache(consumerUpdate.getId());
        ConsumerRes consumerRes = new ConsumerRes();
        consumerRes.setId(consumerUpdate.getId());
        return consumerRes;
    }

    /**
     * 校验token商户会员
     *
     * @param token
     * @param id
     * @return
     */
    private MerchantConsumer checkMerchantConsumer(String token, long id) {
        long merchantId = merchantDomainService.getUserMessage(token).getMerchantId();
        MerchantConsumer merchantConsumer = consumerDomainService.getMerchantConsumer(id);
        if (!merchantConsumer.getMerchantId().equals(merchantId)) {
            throw new BackendException(Code.CODE_PARAM_ERROR, "账户无权限操作会员");
        }
        return merchantConsumer;
    }
}
