package com.lihebin.market.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by lihebin on 2020/10/25.
 */
@Configuration
@MapperScan(basePackages = "com.lihebin.market.sharding")
public class ShardingConfig {

    @Autowired
    private DataSource db0DataSource;

    @Autowired
    private DataSource db1DataSource;

    @Bean
    public DataSource dataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //分片表规则配置tab_user
        TableRuleConfiguration tabUserTableRuleConfig = new TableRuleConfiguration("tab_user", "db${0..1}.tab_user${0..1}");
        tabUserTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "id"));
        tabUserTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ShardingTableAlgorithm()));
        tabUserTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "db${id % 2}"));

        //分片表规则配置tab_password
        TableRuleConfiguration tabPasswordTableRuleConfig = new TableRuleConfiguration("tab_password", "db${0..1}.tab_password${0..1}");
        tabPasswordTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "id"));
        tabPasswordTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ShardingTableAlgorithm()));
        tabPasswordTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "db${id % 2}"));
        List<TableRuleConfiguration> tableRuleConfigurationList = new ArrayList<>();
        tableRuleConfigurationList.add(tabUserTableRuleConfig);
        tableRuleConfigurationList.add(tabPasswordTableRuleConfig);

        shardingRuleConfig.getTableRuleConfigs().addAll(tableRuleConfigurationList);
        //绑定分片表，主要用来路由
        List<String> tableGroups = Arrays.asList("tab_user", " tab_password");
        shardingRuleConfig.getBindingTableGroups().addAll(tableGroups);

//        shardingRuleConfig.getBroadcastTables().add("t_config");

        //分库策略
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "db${id % 2}"));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setFailFast(true);
        sqlSessionFactoryBean.setMapperLocations(resolver.getResource("classpath:/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("db0", db0DataSource);
        dataSourceMap.put("db1", db1DataSource);
        return dataSourceMap;
    }
}
