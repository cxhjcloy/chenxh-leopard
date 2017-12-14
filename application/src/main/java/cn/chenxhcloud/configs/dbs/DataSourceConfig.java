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

/**
 * 
*   
* 项目名称：chenxh-app  
* 类名称：cn.chenxhcloud.configs.dbs.DataSourceConfig  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:02:24
* 描述：同时配置多个数据源，配置动态数据源
*
 */
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
	@Lazy
	@Qualifier("test")	
	private DataSource test;
	
	
	@Lazy
	@Autowired
	@Qualifier("mysql")
	private DataSource mysql;	
	
	
	@Bean("dynamicDataSource")
	@Primary 
	public DynamicDataSource dataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>(10);
		targetDataSources.put("test", test);
		targetDataSources.put("mysql", mysql);
		DataSourceContextHolder.dataSourceIds.add("test");
		DataSourceContextHolder.dataSourceIds.add("mysql");
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);
		//defaultDataSource
		dataSource.setDefaultTargetDataSource(test);		
		dataSource.afterPropertiesSet();
		if(log.isDebugEnabled()) {
			log.debug("--------------------init dynamicDataSource--------------------");
		}
		return dataSource;
	}
}
