package com.company.service;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookService {
	public List<BookDTO> listAll();
	public boolean insert(BookDTO bookDto);
	public BookDTO read(String code);
	public boolean remove(String code);
	public boolean modify(BookDTO updateDto);
}
