package com.bs.config.product;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
@EnableJpaRepositories(
		entityManagerFactoryRef = "db1EntityManagerFactoryBean",
		transactionManagerRef = "db1PlatformTransactionManager",
		basePackages = "com.bs.repository.product"
		
		)
public class DbOneConfig {

//	1. Data source

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "db1.datasource")
	public DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}

//	2. EntityMangerFactory
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactoryBean(EntityManagerFactoryBuilder emfb) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return emfb.dataSource(db1DataSource())
				.packages("com.bs.model.product")
				.properties(properties)
				.build();
	}

//	3. TransactionManager

	@Primary
	@Bean
	public PlatformTransactionManager db1PlatformTransactionManager(
			@Qualifier("db1EntityManagerFactoryBean")
			EntityManagerFactory entityManagerFactory
			) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
