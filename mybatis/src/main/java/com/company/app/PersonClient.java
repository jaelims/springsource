package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.service.PersonService;

public class PersonClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService) ctx.getBean("person");
		
		System.out.println(service.insertPerson("kim123", "김일남") ? "입력성공" : "입력실패");
		
		System.out.println(service.updatePerson("hong123", "홍길동") ? "수정성공" : "수정실패");
		
		System.out.println(service.selectPerson("hong123"));
		
		System.out.println(service.deletePerson("kang123") ? "삭제성공" : "삭제실패");
	}
}
