package com.nagarro.configurations;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableJpaRepositories(basePackages = "com.nagarro")
//@PropertySource("classpath:jpa.properties")
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:jpa.properties" })
@ComponentScan({ "com.nagarro" })
public class JPAConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	  	sessionFactory.setDataSource(dataSource());
	    sessionFactory.setPackagesToScan(
	        new String[] { "com.nagarro" });
	    sessionFactory.setHibernateProperties( additionalProperties() );
	 
	    return sessionFactory;
	}

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setDatabase(Database.MYSQL);
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan("com.nagarro");
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
//
//		return em;
//	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
		SessionFactory sessionFactory) {
	  
		HibernateTransactionManager txManager
		= new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
		
	}
	 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(emf);
//
//		return transactionManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.current_session_context_class",
				env.getProperty("hibernate.current_session_context_class"));
		properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return properties;
	}

}