package com.lihebin.market.service.impl;

import com.lihebin.market.bean.Code;
import com.lihebin.market.dao.OrderDao;
import com.lihebin.market.dao.TransactionDao;
import com.lihebin.market.domain.MerchantDomainService;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.model.Merchant;
import com.lihebin.market.model.Order;
import com.lihebin.market.model.Transaction;
import com.lihebin.market.params.OrderRes;
import com.lihebin.market.params.TransactionRes;
import com.lihebin.market.params.UserMessage;
import com.lihebin.market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2020/3/5.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MerchantDomainService merchantDomainService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TransactionDao transactionDao;


    @Override
    public Page<OrderRes> listOrderPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                          Optional<String> orderSn, Optional<String> name,
                                          Optional<Long> type, Optional<Long> status,
                                          int pageNo, int pageSize) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        Merchant merchant = merchantDomainService.getMerchant(merchantId);
        Page<Order> orderPage = orderDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> merchantIdPath = root.get("merchantId");
            Path<String> snPath = root.get("sn");
            Path<String> namePath = root.get("name");
            Path<Long> typePath = root.get("type");
            Path<Long> statusPath = root.get("status");
            Path<Date> timePath = root.get("ctime");
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(merchantIdPath, userMessage.getMerchantId()));
            name.ifPresent(s -> predicateList.add(criteriaBuilder.like(namePath, "%" + s + "%")));
            orderSn.ifPresent(s -> predicateList.add(criteriaBuilder.equal(snPath, s)));
            type.ifPresent(s -> predicateList.add(criteriaBuilder.equal(typePath, s)));
            status.ifPresent(s -> predicateList.add(criteriaBuilder.equal(statusPath, s)));
            if (ctimeStart.isPresent() && ctimeEnd.isPresent()) {
                predicateList.add(criteriaBuilder.between(timePath, ctimeStart.get(), ctimeEnd.get()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "ctime")));
        return new PageImpl<>(
                orderPage.getContent()
                        .stream()
                        .map(order -> buildOrderRes(order, merchant))
                        .collect(Collectors.toList()),
                orderPage.previousPageable(), orderPage.getTotalElements());
    }

    @Override
    public OrderRes getOrder(String token, long orderId) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        Order order = checkOrder(orderId);
        Merchant merchant = merchantDomainService.getMerchant(merchantId);
        return buildOrderRes(order, merchant);
    }






    @Override
    public List<TransactionRes> listOrderTransaction(String token, long orderId) {
        UserMessage userMessage = merchantDomainService.getUserMessage(token);
        long merchantId = userMessage.getMerchantId();
        Merchant merchant = merchantDomainService.getMerchant(merchantId);
        List<Transaction> transactions = transactionDao.findAllByOrderId(orderId);
        return transactions.stream()
                .map(transaction -> buildTransactionRes(transaction, merchant))
                .collect(Collectors.toList());
    }

    private Order checkOrder(long orderId) {
        Optional<Order> order = orderDao.findById(orderId);
        if (!order.isPresent()) {
            throw new BackendException(Code.CODE_NOT_EXIST, "订单不存在");
        }
        return order.get();
    }
}
