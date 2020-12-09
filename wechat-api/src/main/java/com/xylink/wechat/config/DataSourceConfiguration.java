package com.xylink.wechat.config;


import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
@Configuration
public class DataSourceConfiguration {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Bean(destroyMethod = "", name = "EmbeddedDataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(jdbcUrl);
        return dataSourceBuilder.build();
    }

}
