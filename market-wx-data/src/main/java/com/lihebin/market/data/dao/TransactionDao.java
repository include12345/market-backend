package com.lihebin.market.data.dao;

import com.lihebin.market.data.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihebin on 2019/4/16.
 */
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    /**
     * 根据orderId查询订单流水
     *
     * @return
     */
    List<Transaction> findAllByOrderId(long orderId);
}
