package cn.chenxhcloud.configs.dbs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;


@Configuration
public class DataSourceConfig {
	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean(name = "test")
	@ConfigurationProperties(prefix = "spring.mydatasources.test")
	public DataSource test() {
		if(log.isDebugEnabled()) {
			log.debug("--------------------init datasource test--------------------");
		}
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mysql")
	@ConfigurationProperties(prefix = "spring.mydatasources.mysql")
	public DataSource cjyun() {
		if(log.isDebugEnabled()) {
			log.debug("--------------------init datasource mysql--------------------");
		}
		return DataSourceBuilder.create().build();
	}

	@Autowired
	@Lazy//延迟加载
	@Qualifier("test")
	private DataSource test;
	@Lazy
	@Autowired
	@Qualifier("mysql")
	private DataSource mysql;
	

	@Bean("dynamicDataSource")
	@Primary //Primary DataSource
	public DynamicDataSource dataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("test", test);
		targetDataSources.put("mysql", mysql);
		DataSourceContextHolder.dataSourceIds.add("test");
		DataSourceContextHolder.dataSourceIds.add("mysql");
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(test);//defaultDataSource
		dataSource.afterPropertiesSet();
		if(log.isDebugEnabled()) {
			log.debug("--------------------init dynamicDataSource--------------------");
		}
		return dataSource;
	}
}
