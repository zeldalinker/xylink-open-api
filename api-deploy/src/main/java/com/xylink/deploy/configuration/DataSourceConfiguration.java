package com.xylink.deploy.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration {
	@Value("${jdbc.driverClassName}")
	String driverClassName;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String username;
	@Value("${jdbc.password}")
	String password;
	@Value("${nacos.server-addr}")
	private String configAddr;


	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {
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

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory(@Autowired @Qualifier("dataSource") DataSource dataSource)
//            throws IOException {
//		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactory.setConfigLocation(resolver.getResource("sqlMapConfig.xml"));
//        sqlSessionFactory.setMapperLocations(resolver.getResources("com/xylink/admin/dao/sql/*Mapper.xml"));
//        return sqlSessionFactory;
//    }



}
