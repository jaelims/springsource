package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.persistence.BookDAO;

// BookServiceImpl 객체 생성 단, id명은 bookServiceImpl
@Service // == @Component
public class BookServiceImpl implements BookService {

	@Autowired // 주입(생성된 객체 넣어줘)
	private BookDAO dao;
	
	@Override
	public List<BookDTO> getList() {
		return dao.list();
	}

	@Override
	public boolean insertBook(BookDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public boolean deleteBook(String code) {
		return dao.delete(code);
	}

	@Override
	public boolean updateBook(String code, int price) {
		return dao.update(code, price);
	}

}
