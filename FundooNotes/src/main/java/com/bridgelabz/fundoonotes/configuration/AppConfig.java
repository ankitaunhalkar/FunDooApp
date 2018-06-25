package com.bridgelabz.fundoonotes.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgelabz.fundoonotes.model.User;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.bridgelabz.fundoonotes" })
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getdataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));

		return dataSource;

	}

	@Bean
	public LocalSessionFactoryBean getSesssionFactory() {

		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		
		Properties prop = new Properties();
		
		factory.setDataSource(getdataSource());

		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		factory.setHibernateProperties(prop);
		factory.setAnnotatedClasses(User.class);

		return factory;

	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManger = new HibernateTransactionManager();
		transactionManger.setSessionFactory(getSesssionFactory().getObject());
		return transactionManger;
	}
}
