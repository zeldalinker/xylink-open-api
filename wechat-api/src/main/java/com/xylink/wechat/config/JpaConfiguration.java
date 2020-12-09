package com.xylink.wechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.xylink.wechat.dao",
        transactionManagerRef = "jpaTransactionManager",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
)
public class JpaConfiguration {
    @Resource
    private JpaProperties jpaProperties;

    @Autowired
    @Bean
    public JpaTransactionManager jpaTransactionManager(@Qualifier(value = "EmbeddedDataSource") DataSource dataSource,
                                                       EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager
                = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }

    @Autowired
    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier(value =
            "EmbeddedDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages("com.xylink.wechat")
                .properties(getVendorProperties(dataSource))
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

}