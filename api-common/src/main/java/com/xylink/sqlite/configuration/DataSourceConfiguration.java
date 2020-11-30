package com.xylink.sqlite.configuration;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
@Configuration
@PropertySource(value = {
        "classpath*:resources/sqlite.properties",
}, encoding = "utf-8")
public class DataSourceConfiguration {

    @Bean(destroyMethod = "", name = "EmbeddedDataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite::resource:db/meeting.sqlite");
        dataSourceBuilder.type(SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }

}
