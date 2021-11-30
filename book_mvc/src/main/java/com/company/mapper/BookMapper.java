package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO> all();
	public int insert(BookDTO bookDto);
	public BookDTO read(String code);
	public int remove(String code);
	public int modify(@Param("price") int price, @Param("code") String code);
}
