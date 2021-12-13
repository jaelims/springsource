package com.company.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan("com.company.mapper") // <mybatis-spring:scan base-package="com.company.mapper"/>
@ComponentScan("com.company.service") // <context:component-scan base-package="com.company.service"/>
@Configuration
public class RootConfig {

//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="ds"></property>
//	</bean>
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
//	<!-- DBCP(데이터베이스 커넥션 풀링) : HikariCP -->
//	<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
//		<property name="driverClassName" value="oracle.jdbc.OracleDriver" />
//		<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
//		<property name="username" value="c##java" />
//		<property name="password" value="12345" />
//	</bean>
//	
//	<!-- DBCP 사용할 때 커넥션을 얻어올 때 DataSource 사용 -->
//	<bean id="ds" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
//		<constructor-arg ref="hikariConfig" />
//	</bean>
	
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
