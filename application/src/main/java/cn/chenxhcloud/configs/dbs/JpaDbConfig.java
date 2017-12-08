package cn.chenxhcloud.configs.dbs;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMyDs", transactionManagerRef = "transactionManagerMyDs", basePackages = {"cn.chenxhcloud.services" })
public class JpaDbConfig {

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	@Qualifier("dynamicDataSource") //动态数据源
	private DataSource myDs;

	@Primary
	@Bean(name = "entityManagerFactoryMyDs")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryKit(EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(myDs).properties(getVendorProperties(myDs)).packages("cn.chenxhcloud.services").persistenceUnit("myDsPersistenceUnit").build();
		return em;
	}

	@Primary
	@Bean(name = "transactionManagerMyDs")
	PlatformTransactionManager transactionManagerKit(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryKit(builder).getObject());
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}
}
