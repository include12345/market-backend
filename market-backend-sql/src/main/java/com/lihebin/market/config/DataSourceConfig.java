package com.lihebin.market.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by lihebin on 2020/2/27.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name= "marketDataSource")
    @Primary
    @Qualifier("marketDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.market")
    public DataSource marketDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }


    @Bean(name = "marketJdbcTemplate")
    public JdbcTemplate  marketJdbcTemplate(@Qualifier("marketDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(3);
        return jdbcTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db0")
    public DataSource db0DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
