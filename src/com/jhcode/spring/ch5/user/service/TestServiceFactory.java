package com.jhcode.spring.ch5.user.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.jhcode.spring.ch5.user.dao.UserDaoJdbc;


@Configuration
public class TestServiceFactory {
	
	@Bean
	public UserDaoJdbc userDao() {
		UserDaoJdbc userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		String url = "jdbc:mariadb://localhost:3306/toby_study?characterEncoding=UTF-8";
		String username = "root";
		String password = "1234";
		
		dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public UserService userService() {
		UserService userService = new UserService();
		UserLevelUpgradePolicy userLevelUpgradePolicy = new UserLevelUpgradeImpl();
		userService.setUserDao(userDao());
		userService.setUserLevelUpgradePolicy(userLevelUpgradePolicy);
		return userService;
	}
}
