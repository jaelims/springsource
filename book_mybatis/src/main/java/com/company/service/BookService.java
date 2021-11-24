package com.company.service;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookService {
	// 특정 도서 가져오기
	public BookDTO getRow(String code);
	// 전체 도서 목록
	public List<BookDTO> getList();
	// 새로운 도서 입력
	public boolean insertBook(BookDTO dto);
	// 도서 삭제
	public boolean deleteBook(String code);
	// 도서 수정
	public boolean updateBook(String code, int price);
}
