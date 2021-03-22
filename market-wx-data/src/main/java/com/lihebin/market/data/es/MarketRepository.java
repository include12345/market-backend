package com.lihebin.market.data.es;

import com.lihebin.market.data.es.model.MarketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

/**
 * Created by lihebin on 2020/8/17.
 */
@Repository
public interface MarketRepository extends ElasticsearchRepository<MarketModel, Long>{

    @Query("{\"bool\" {\"must\": {\"field\" :{\"title\" :\"?0\"}}}}")
    Page<MarketModel> findByTitle(String title, Pageable pageable);


}
