package com.xylink.admin.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.xylink.admin.handler.MyMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.xylink.admin")
public class DataSourceConfiguration{

	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName,
									  @Value("${jdbc.url}") String url, @Value("${jdbc.username}") String username,
									  @Value("${jdbc.password}") String password) throws SQLException {

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		dataSource.setFilters("stat");

		dataSource.setMaxActive(20);
		dataSource.setInitialSize(1);
		dataSource.setMaxWait(60000);
		dataSource.setMinIdle(1);

		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);

		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);

		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxOpenPreparedStatements(20);

		return dataSource;
	}

	@Bean(name = "sqlSessionFactory")
	public MybatisSqlSessionFactoryBean sqlSessionFactory(@Autowired @Qualifier("dataSource")  DataSource dataSource)
			throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		//全局配置
		GlobalConfig globalConfig  = new GlobalConfig();
		//配置填充器
		globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
		sqlSessionFactory.setGlobalConfig(globalConfig);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setConfigLocation(resolver.getResource("sqlMapConfig.xml"));
		sqlSessionFactory.setMapperLocations(resolver.getResources("com/xylink/admin/dao/sql/*Mapper.xml"));
		return sqlSessionFactory;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("dataSource") DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
}
