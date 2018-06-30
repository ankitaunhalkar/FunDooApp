package com.bridgelabz.fundoonotes.user.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgelabz.fundoonotes.user.model.Email;
import com.bridgelabz.fundoonotes.user.model.User;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.bridgelabz.fundoonotes" })
@Import({MessagingConfiguration.class,MessagingListnerConfiguration.class})
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
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		     
		mailSender.setHost(env.getProperty("spring.mail.host"));
		mailSender.setUsername(env.getProperty("spring.mail.username"));
		mailSender.setPassword(env.getProperty("spring.mail.password"));
		mailSender.setPort(587);
		
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("spring.mail.transport.protocol", env.getProperty("spring.mail.transport.protocol"));
		    props.put("spring.mail.properties.mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
		    props.put("spring.mail.properties.mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
		     
		   
		return mailSender;
		
	}
	@Bean
	public User user() {
		return new User();
	}
	
	@Bean
	public Email email() {
		return new Email();
	}
	
	
}