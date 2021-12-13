package com.company.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableScheduling
@MapperScan("com.company.mapper")
@EnableTransactionManagement
@ComponentScan({"com.company.service", "com.company.task"})
@Configuration
public class RootConfig {
	
	//<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	//	<property name="dataSource" ref="ds"/>
	//</bean>
	
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	//	<property name="dataSource" ref="ds"></property>
	//</bean>

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}

	//<!-- DBCP(데이터베이스 커넥션 풀링) : HikariCP -->
	//<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
	//	<property name="driverClassName" value="oracle.jdbc.OracleDriver" />
	//	<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
	//	<property name="username" value="c##java" />
	//	<property name="password" value="12345" />
	//</bean>
	//
	//<!-- DBCP 사용할 때 커넥션을 얻어올 때 DataSource 사용 -->
	//<bean id="ds" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
	//	<constructor-arg ref="hikariConfig" />
	//</bean>

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("c##java");
		hikariConfig.setPassword("12345");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	


}
