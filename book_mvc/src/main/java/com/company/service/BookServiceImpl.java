package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;
	
	@Override
	public List<BookDTO> listAll() {
		return mapper.all();
	}

	@Override
	public boolean insert(BookDTO bookDto) {
		return mapper.insert(bookDto) > 0 ? true : false;
	}

	@Override
	public BookDTO read(String code) {
		return mapper.read(code);
	}

}
