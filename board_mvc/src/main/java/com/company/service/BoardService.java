package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardService {
	public boolean register(BoardDTO insertDto);
	public List<BoardDTO> getRows();
	public BoardDTO read(int bno);
	public boolean update(BoardDTO modifyDto);
	public boolean remove(int bno);
}
