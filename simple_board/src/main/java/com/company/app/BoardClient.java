package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

public class BoardClient {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		BoardService service = (BoardService) ctx.getBean("boardService");
		
		// 게시물 입력
//		BoardDTO insertDto = new BoardDTO();
//		insertDto.setTitle("스프링 프레임워크");
//		insertDto.setContent("스프링 프레임워크 게시판");
//		insertDto.setWriter("홍길동");
//		
//		System.out.println(service.insertBoard(insertDto)? "입력성공":"입력실패");
		
		// 게시물 삭제
		System.out.println(service.deleteBoard(3)? "삭제성공":"삭제실패");
		
		// 게시물 수정
//		BoardDTO update = new BoardDTO();
//		update.setBno(1);
//		update.setTitle("스프링 수정");
//		update.setContent("스프링 게시판");
//		System.out.println(service.updateBoard(update)? "수정성공":"수정실패");
		
		
		// 특정 게시물 조회
//		BoardDTO row = service.getRow(1);
//		System.out.print(row.getBno()+"\t");
//		System.out.print(row.getTitle()+"\t");
//		System.out.print(row.getContent()+"\t");
//		System.out.print(row.getWriter()+"\t");
//		System.out.print(row.getRegdate()+"\t");
//		System.out.print(row.getUpdatedate()+"\n");
		
		System.out.println();
		
		// 전체 게시물 조회
		List<BoardDTO> list = service.getRows();
		System.out.println("----------------------------------------------");
		System.out.println("bno\t title\t content\t writer\t regdate\t updatedate");
		System.out.println("----------------------------------------------");
		for(BoardDTO board:list) {
			System.out.print(board.getBno()+"\t");
			System.out.print(board.getTitle()+"\t");
			System.out.print(board.getContent()+"\t");
			System.out.print(board.getWriter()+"\t");
			System.out.print(board.getRegdate()+"\t");
			System.out.print(board.getUpdatedate()+"\n");
		}
	}
}
