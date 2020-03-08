package com.lihebin.market.service;


import com.lihebin.market.model.Merchant;
import com.lihebin.market.model.Order;
import com.lihebin.market.model.Transaction;
import com.lihebin.market.params.OrderRes;
import com.lihebin.market.params.TransactionRes;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional; /**
 * Created by lihebin on 2020/3/1.
 */
public interface OrderService {

    Page<OrderRes> listOrderPaging(String token, Optional<Date> ctimeStart, Optional<Date> ctimeEnd,
                                   Optional<String> orderSn, Optional<String> name,
                                   Optional<Long> type, Optional<Long> status,
                                   int pageNo, int pageSize);


    OrderRes getOrder(String token, long orderId);


    List<TransactionRes> listOrderTransaction(String token, long orderId);


    /**
     * build OrderRes
     *
     * @param order
     * @return
     */
    default OrderRes buildOrderRes(Order order, Merchant merchant) {
        OrderRes orderRes = new OrderRes();
        orderRes.setId(order.getId());
        orderRes.setName(order.getName());
        orderRes.setOrderSn(order.getSn());
        orderRes.setDiscount(order.getDiscount());
        orderRes.setOriginalAmount(order.getOriginalAmount());
        orderRes.setPayAmount(order.getPayAmount());
        orderRes.setRemark(order.getRemark());
        orderRes.setStatus(order.getStatus());
        orderRes.setType(order.getType());
        orderRes.setMerchantId(order.getMerchantId());
        orderRes.setMerchantName(merchant.getName());
        orderRes.setCtime(order.getCtime());
        orderRes.setMtime(order.getMtime());
        return orderRes;
    }

    /**
     * build TransactionRes
     *
     * @param transaction
     * @return
     */
    default TransactionRes buildTransactionRes(Transaction transaction, Merchant merchant) {
        TransactionRes transactionRes = new TransactionRes();
        transactionRes.setId(transaction.getId());
        transactionRes.setName(transaction.getName());
        transactionRes.setOrderSn(transaction.getOrderSn());
        transactionRes.setDiscount(transaction.getDiscount());
        transactionRes.setOriginalAmount(transaction.getOriginalAmount());
        transactionRes.setPayAmount(transaction.getPayAmount());
        transactionRes.setRemark(transaction.getRemark());
        transactionRes.setStatus(transaction.getStatus());
        transactionRes.setType(transaction.getType());
        transactionRes.setMerchantId(transaction.getMerchantId());
        transactionRes.setMerchantName(merchant.getName());
        transactionRes.setCtime(transaction.getCtime());
        transactionRes.setMtime(transaction.getMtime());
        return transactionRes;
    }
}
