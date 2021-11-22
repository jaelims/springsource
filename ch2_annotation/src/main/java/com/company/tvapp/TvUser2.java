package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser2 {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		LgTv tv = (LgTv) ctx.getBean("lg");

		tv.turnOn();
		tv.soundDown();
		tv.soundUp();
		tv.turnOff();
	}

}
