package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		// NoSuchBeanDefinitionException: No bean named 'samsung' available
		TV tv = (TV) ctx.getBean("samsung");
		
		tv.turnOn();
		tv.soundDown();
		tv.soundUp();
		tv.turnOff();
		
	}

}
