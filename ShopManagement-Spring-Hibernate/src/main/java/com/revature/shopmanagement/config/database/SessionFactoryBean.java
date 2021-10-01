package com.revature.shopmanagement.config.database;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class SessionFactoryBean {

	private DataSource dataSource;
	private Environment environment;

	public SessionFactoryBean(DataSource dataSource, Environment environment) {
		this.dataSource = dataSource;
		this.environment = environment;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionFactoryBuilder.scanPackages("com.revature.shopmanagement.entity");
		sessionFactoryBuilder.addProperties(getHibernateProperties());
		return sessionFactoryBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		/*
		 * properties.put("hibernate.connection.autocommit",
		 * environment.getProperty("spring.jpa.properties.hibernate.autocommit"));
		 */
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));

		return properties;
	}
}
