package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BookDTO;
import com.example.demo.service.BookService;

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
	
	// /book/read or /book/modify
	@GetMapping({"/read", "/modify"})
	public void readGet(String code, Model model) {
		log.info("read or modify 요청" + code);
		
		BookDTO dto = service.read(code);
		
		model.addAttribute("dto", dto);
		
		// /book/read => /WEB-INF/views/book/read.jsp
		// /book/modify => /WEB-INF/views/book/modify.jsp
	}
	
	// /book/modify + POST
	// 수정이 완료된 후 내용보기
	@PostMapping("/update")
	public String modifyPost(String code, int price, RedirectAttributes rttr) {
		log.info("수정 요청");
		
		service.modify(price, code);
		
		rttr.addAttribute("code", code);
		//return "redirect:/book/read?code="+code;
		return "redirect:/book/read";
	}
	
	
	
	// /book/remove
	@GetMapping("/remove")
	public String removePost(String code) {
		log.info("도서 삭제 요청 " + code);
		
		service.remove(code);
		
		return "redirect:/book/list";
	}
	
}
