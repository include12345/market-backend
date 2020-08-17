package com.lihebin.market.es;

import com.lihebin.market.es.model.MarketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.awt.print.Pageable;
import java.util.Map;

/**
 * Created by lihebin on 2020/8/17.
 */
public interface MarketRepository extends ElasticsearchRepository<MarketModel, Long>{

    @Query("{\"bool\" {\"must\": {\"field\" :{\"title\" :\"?0\"}}}}")
    Page<MarketModel> findByTitle(String title, Pageable pageable);


}
