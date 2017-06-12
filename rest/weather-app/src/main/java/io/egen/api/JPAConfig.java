package io.egen.api;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfig {
	
	/**
	 * creates and return the entity manager bean and returns athe same
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan("io.egen.api.entity");
		emf.setJpaProperties(jpaProperties());
		return emf;
	}
	
	/**
	 * Configure the driver
	 * Setup the database
	 * provide credential for the datavase
	 * @return
	 */

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/weather-db?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}

	/**
	 * Creates the dialect for MySQL
	 * Setup Configuration to create-drop intilally to create table in weather-db
	 * setup configuration to show the SQL Quires in the console
	 * @return
	 */
	
	private Properties jpaProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dilate", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "validate");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true ");

		return prop;
	}
	
	/**
	 * Transaction Manager 
	 * @param emf
	 * @return
	 */
	@Bean
	public PlatformTransactionManager tnxManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
}
