package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		
		System.out.println("========== 스프링 컨테이너 구동 전 =========");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConfig.xml");
		
		System.out.println("========== 스프링 컨테이너 구동 =========");
		
		MessageBean msg = (MessageBean) ctx.getBean("ko");
		msg.sayHello("홍길동");
	}
}
