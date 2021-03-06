package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookDTO;

@Repository // == @Component
public class BookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// insert
	public boolean insert(BookDTO insertDto) {
		String sql = "insert into bookTBL(code,title,writer,price) values(?,?,?,?)";
		int result = jdbcTemplate.update(sql, insertDto.getCode(), insertDto.getTitle(), insertDto.getWriter(), insertDto.getPrice());
		return result > 0 ? true : false;
	}
	
	// delete
	public boolean delete(String code) {
		String sql = "delete from bookTBL where code=?";
		int result = jdbcTemplate.update(sql, code);
		return result > 0 ? true : false;
	}
	
	// update
	public boolean update(String code, int price) {
		String sql = "update bookTBL set price=? where code=?";
		int result = jdbcTemplate.update(sql, code, price);
		return result > 0 ? true : false;
	}
	
	// select - 전체 조회
	public List<BookDTO> list(){
		String sql = "select * from bookTBL";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}
	
	// 검색
	public List<BookDTO> search(String criteria, String keyword){
		String sql = "select * from bookTBL where "+criteria+" = ?";
		return jdbcTemplate.query(sql, new BookRowMapper(), keyword);
	}
	
	
}
