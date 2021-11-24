package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	public List<BoardDTO> list();
	public BoardDTO read(int bno);
	public int insert(BoardDTO dto);
	public int delete(int bno);
	public int update(BoardDTO dto);
}
