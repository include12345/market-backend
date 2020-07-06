package com.lihebin.market.mongo.dao;

import com.lihebin.market.mongo.entity.ChatRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lihebin on 2020/6/30.
 */
@Repository
public interface ChatRecordDao extends CrudRepository<ChatRecordEntity, String>, PagingAndSortingRepository<ChatRecordEntity, String>{

    List<ChatRecordEntity> findByToAndStatus(String to, Integer status);


//    @Query("{\n" +
//            "\t'$and': [{\n" +
//            "\t\t'$or': [{\n" +
//            "\t\t\t'$and': [{\n" +
//            "\t\t\t\t'from': ? 0\n" +
//            "\t\t\t}, {\n" +
//            "\t\t\t\t'to': ? 1\n" +
//            "\t\t\t}]\n" +
//            "\t\t}, {\n" +
//            "\t\t\t'$and': [{\n" +
//            "\t\t\t\t'to': ? 0\n" +
//            "\t\t\t}, {\n" +
//            "\t\t\t\t'from': ? 1\n" +
//            "\t\t\t}]\n" +
//            "\t\t}]\n" +
//            "\t}, {\n" +
//            "\t\t'ctime': {\n" +
//            "\t\t\t'$gte': 20,\n" +
//            "\t\t\t'$lte': 30\n" +
//            "\t\t}\n" +
//            "\t}]\n" +
//            "}")
//    List<ChatRecordEntity> findByFromOrTo(String from, String to, long ctimeStart, long ctimeEnd);



    List<ChatRecordEntity> findAllByFromAndToAndStatus(String from, String to, long status);


    List<ChatRecordEntity> findAllByToAndStatus(String to, long status);



    @Query("{\n" +
            "\t'$and': [{\n" +
            "\t\t'$or': [{\n" +
            "\t\t\t'$and': [{\n" +
            "\t\t\t\t'from': ?0\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t'to': ?1\n" +
            "\t\t\t}]\n" +
            "\t\t}, {\n" +
            "\t\t\t'$and': [{\n" +
            "\t\t\t\t'to': ?0\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t'from': ?1\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t'ctime': {\n" +
            "\t\t\t'$gte': ?2,\n" +
            "\t\t\t'$lte': ?3\n" +
            "\t\t}\n" +
            "\t}]\n" +
            "}")
    Page<ChatRecordEntity> findByFromOrToPage(String from, String to, long ctimeStart, long ctimeEnd, Pageable pageable);




}
