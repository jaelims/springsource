package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.persistence.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Override
	public boolean insertBoard(BoardDTO insertDto) {
		return dao.insert(insertDto);
	}

	@Override
	public boolean deleteBoard(int bno) {
		return dao.delete(bno);
	}

	@Override
	public BoardDTO getRow(int bno) {
		return dao.getRow(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		return dao.list();
	}

	@Override
	public boolean updateBoard(BoardDTO updateDto) {
		return dao.update(updateDto);
	}

}
