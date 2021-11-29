package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	// insert.jsp 보여주는 컨트롤러 생성
	@GetMapping("/insert")
	public void insertGet() {
		log.info("insert.jsp 페이지 요청");
	}
	
	// 새 도서 입력
	// 입력성공 시 전체 목록 보기
	@PostMapping("/insert")
	public String insertPost(BookDTO bookDto) {
		log.info("도서입력");
		
		try {
			if(service.insert(bookDto)) {
				return "redirect:list";
			}
		} catch (Exception e) {
			return "/book/insert";
		}
		return "/book/insert";
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list.jsp 페이지 요청");
		List<BookDTO> list = service.listAll();
		
		model.addAttribute("list", list);
	}
	
	// /book/read
	@GetMapping("/read")
	public void readGet(String code, Model model) {
		log.info("read 요청" + code);
		BookDTO readDto = service.read(code);
		
		model.addAttribute("read", readDto);
	}
	
}
