package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public boolean register(BoardDTO insertDto) {
		return mapper.register(insertDto) > 0 ? true : false;
	}

	@Override
	public List<BoardDTO> getRows() {
		return mapper.listAll();
	}

	@Override
	public BoardDTO read(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean update(BoardDTO modifyDto) {
		return mapper.modify(modifyDto) > 0 ? true : false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno) > 0 ? true : false;
	}

}
