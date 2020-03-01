package com.lihebin.market.config;

import com.lihebin.market.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Created by lihebin on 2020/2/26.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryMarket",
        transactionManagerRef = "transactionManagerMarket",
        basePackages = {"com.lihebin.market.dao"}
)
public class MarketJpaConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;


    @Primary
    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMarket(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMarket")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMarket(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource).packages("com.lihebin.market.model")//设置实体类所在位置
                .properties(jpaProperties.getProperties())
                .persistenceUnit("marketPersistenceUnit")//持久化单元创建一个默认即可，多个便要分别命名
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManagerMarket(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMarket(builder).getObject());
    }

}
