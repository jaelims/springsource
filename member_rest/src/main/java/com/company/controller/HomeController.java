package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j2
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		// index.jsp
		log.info("index 요청");
		
		return "index";
	}
	
	@GetMapping("/insert")
	public String insert() {
		log.info("insert 요청 ");
		return "/rest/insert";
	}
	
	@GetMapping("/changePwd")
	public String changePwd() {
		log.info("changePwd.jsp 요청");
		return "/rest/changePwd";
	}
	
	// 회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public String leaveGet() {
		log.info("회원탈퇴 페이지 요청");
		return "/rest/leave";
	}
}
