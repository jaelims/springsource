package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BookDTO;

public interface BookService {
	public List<BookDTO> listAll();
	public boolean insert(BookDTO bookDto);
	public BookDTO read(String code);
	public boolean remove(String code);
	public boolean modify(int password, String code);
}
