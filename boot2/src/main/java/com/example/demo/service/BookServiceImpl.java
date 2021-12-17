package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BookDTO;
import com.example.demo.mapper.BookMapper;

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

	@Override
	public boolean remove(String code) {
		return mapper.remove(code) > 0 ? true : false;
	}

	@Override
	public boolean modify(int password, String code) {
		return mapper.modify(password, code) > 0 ? true : false;
	}

}
