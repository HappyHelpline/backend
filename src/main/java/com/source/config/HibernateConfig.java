package com.source.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.source.config" })
@PropertySource(value = {"classpath:application.properties" })
@EnableWebMvc
public class HibernateConfig {
	
    @Autowired
    private Environment environment;
	   @Bean
	   public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[] { "com.source.model"});
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	     }
	   
	   @Bean
	    public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	        return dataSource;
	    }
	   
	   
	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("hibernate.c3p0.min_size",environment.getProperty("hibernate.c3p0.min_size"));
	        properties.put("hibernate.c3p0.max_size",environment.getProperty("hibernate.c3p0.max_size"));
	        properties.put("hibernate.c3p0.timeout",environment.getProperty("hibernate.c3p0.timeout"));
	        properties.put("hibernate.c3p0.max_statements",environment.getProperty("hibernate.c3p0.max_statements"));
	        properties.put("hibernate.c3p0.idle_test_period",environment.getProperty("hibernate.c3p0.idle_test_period"));
	        return properties;        
	    }
	    
	    
	    @Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager( SessionFactory s) {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(s);
	       System.out.println("Connection Etablish");
	       return txManager;
	    }

}
