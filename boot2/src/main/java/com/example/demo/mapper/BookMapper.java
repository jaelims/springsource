package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.BookDTO;

@Mapper
public interface BookMapper {
	public List<BookDTO> all();
	public int insert(BookDTO bookDto);
	public BookDTO read(String code);
	public int remove(String code);
	public int modify(@Param("price") int price, @Param("code") String code);
}
