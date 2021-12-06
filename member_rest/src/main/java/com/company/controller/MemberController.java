package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 로그인
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 페이지 요청");
		// /member/signin
	}
	
	// loginPost()
	// loginDTO 작성, userid, password, name
	@PostMapping("/signin")
	public String loginPost(LoginDTO loginDto, HttpSession session) {
		log.info("로그인 요청" + loginDto);
		
		loginDto = service.login(loginDto);
		
		session.setAttribute("loginDto", loginDto);
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청");
		session.invalidate();
		
		return "redirect:/";
	}
	
	// /changePwd : changePwd.jsp 보여주는 컨트롤러
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("비밀번호 변경 페이지 요청");
	}
	
	// changePwd(POST) + 폼에서 넘기는 값 가져오기(ChangeDTO)
	@PostMapping("/changePwd")
	public String changePwd(ChangeDTO changeDto, HttpSession session) {
		log.info("비밀번호 변경 요청" + changeDto);
		
		// 비밀번호 변경 요청
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		changeDto.setUserid(loginDto.getUserid());
		
		// 비밀번호 변경이 되면 기존의 세션 해제
		// signin 페이지 보여주기
		if (service.changePwd(changeDto)) {
			session.invalidate();
			return "redirect:/member/signin";
		}
		// 변경 실패시 changePwd 보여주기
		return "redirect:/member/changePwd";
	}
	
	// 회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴 페이지 요청");
	}
	
	
	// 회원탈퇴
	@PostMapping("/leave")
	public String leavePost(LoginDTO leaveDto, HttpSession session) {
		// userid, password
		log.info("회원탈퇴 요청" + leaveDto);
		
		if(service.leave(leaveDto)) {
			session.invalidate();
			return "redirect:/";
		}
		return "redirect:/member/leave";
	}
	
}
