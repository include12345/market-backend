package com.lihebin.market.dao;

import com.lihebin.market.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lihebin on 2019/4/16.
 */
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
