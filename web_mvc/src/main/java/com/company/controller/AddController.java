package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.AddDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/calc/*")
public class AddController {
	
	@GetMapping("/add")
	public void addGet() {
		log.info("/calc/add 요청");
	}
	
//	@PostMapping("/add")
//	public void addPost(int num1, int num2) {
//		log.info("/calc/add post 요청");
//		log.info("num1 : " + num1);
//		log.info("num2 : " + num2);
//	}
	
	@PostMapping("/add")
	public void addPost(AddDTO addDto, Model model) {
		log.info("/calc/add post 요청");
		log.info(addDto.getNum1() + ", " + addDto.getNum2());
		
		// 덧셈한 후 결과를 add.jsp 보여주기
		int result = addDto.getNum1()+addDto.getNum2();
		model.addAttribute("result", result);
	}
}
