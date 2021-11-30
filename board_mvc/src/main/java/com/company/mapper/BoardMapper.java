package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	public int register(BoardDTO boardDto);
	public List<BoardDTO> listAll();
	public BoardDTO read(int bno);
	public int modify(BoardDTO modifyDto);
	public int remove(int bno);
}
