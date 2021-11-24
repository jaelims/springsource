package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

public class MemberClient {
	public static void main(String[] args) {
		
		// 스프링 컨테이너 로드
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// 서비스 메소드 호출
		MemberService service = (MemberService) ctx.getBean("service");
		
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		while (flag) {
			System.out.println("======================================");
			System.out.println("1. 전체 멤버 조회");
			System.out.println("2. 특정 멤버 조회");
			System.out.println("3. 특정 멤버 수정");
			System.out.println("4. 특정 멤버 삭제");
			System.out.println("5. 특정 멤버 추가");
			System.out.println("6. 프로그램 종료");
			System.out.println("======================================");
			
			System.out.print("메뉴 >> ");
			int no = Integer.parseInt(sc.nextLine());
			
			switch (no) {
			case 1:
				System.out.println();
				System.out.println("아이디\t성명\t성별\t이메일");
				System.out.println("-----------------------------------------");
				
				List<MemberDTO> list = service.getList();
				for(MemberDTO dto:list) {
					System.out.print(dto.getUserid()+"\t");
					System.out.print(dto.getName()+"\t");
					System.out.print(dto.getGender()+"\t");
					System.out.print(dto.getEmail()+"\n");
				}
				System.out.println();
				break;
			case 2:
				System.out.println("조회할 사용자 정보 입력");
				System.out.println("---------------------------");
				System.out.print("아이디 : ");
				String userid = sc.nextLine();
				System.out.print("비밀번호 : ");
				String password = sc.nextLine();
				MemberDTO dto = service.getRow(userid, password);
				
				System.out.println();
				System.out.println("아이디\t성명\t성별\t이메일");
				System.out.println("-----------------------------------------");

				System.out.print(dto.getUserid()+"\t");
				System.out.print(dto.getName()+"\t");
				System.out.print(dto.getGender()+"\t");
				System.out.print(dto.getEmail()+"\n");
				System.out.println();
				break;
			case 3:
				System.out.println("수정할 사용자 정보 입력");
				System.out.println("---------------------------");
				ChangeDTO changeDto = new ChangeDTO();
				System.out.print("아이디 : ");
				changeDto.setUserid(sc.nextLine());
				System.out.print("비밀번호 : ");
				changeDto.setPassword(sc.nextLine());
				System.out.print("새로운 비밀번호 : ");
				changeDto.setConfirm_password(sc.nextLine());
				
				System.out.println(service.memberUpdate(changeDto) ? "수정성공" : "수정실패");
				
				break;
			case 4:
				System.out.println("삭제할 사용자 정보 입력");
				System.out.println("---------------------------");
				System.out.print("아이디 : ");
				userid = sc.nextLine();
				System.out.print("비밀번호 : ");
				password = sc.nextLine();
				
				System.out.println(service.memberDelete(userid, password) ? "삭제성공" : "삭제실패");
				break;
			case 5:
				System.out.println("새로운 사용자 정보 입력");
				System.out.println("---------------------------");
				MemberDTO insertDto = new MemberDTO();
				System.out.print("아이디 : ");
				insertDto.setUserid(sc.nextLine());
				System.out.print("비밀번호 : ");
				insertDto.setPassword(sc.nextLine());
				System.out.print("이름 : ");
				insertDto.setName(sc.nextLine());
				System.out.print("성별 : ");
				insertDto.setGender(sc.nextLine());
				System.out.print("이메일 : ");
				insertDto.setEmail(sc.nextLine());
				
				System.out.println(service.memberInsert(insertDto) ? "생성성공" : "생성실패");
				break;
			case 6:
				flag = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
