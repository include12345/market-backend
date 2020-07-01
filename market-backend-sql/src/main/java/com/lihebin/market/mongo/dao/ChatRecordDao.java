package com.lihebin.market.mongo.dao;

import com.lihebin.market.mongo.entity.ChatRecordEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lihebin on 2020/6/30.
 */
public interface ChatRecordDao extends CrudRepository<ChatRecordEntity, String>{

    List<ChatRecordEntity> findByToAndStatus(String to, Integer status);


    @Query("{\n" +
            "\t'$and': [{\n" +
            "\t\t'$or': [{\n" +
            "\t\t\t'$and': [{\n" +
            "\t\t\t\t'from': ? 0\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t'to': ? 1\n" +
            "\t\t\t}]\n" +
            "\t\t}, {\n" +
            "\t\t\t'$and': [{\n" +
            "\t\t\t\t'to': ? 0\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t'from': ? 1\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t'ctime': {\n" +
            "\t\t\t'$gte': 20,\n" +
            "\t\t\t'$lte': 30\n" +
            "\t\t}\n" +
            "\t}]\n" +
            "}")
    List<ChatRecordEntity> findByFromOrTo(String from, String to, long ctimeStart, long ctimeEnd);

}
