package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Transactional
	@Override
	public boolean register(BoardDTO insertDto) {
		
		// 게시물 등록
		boolean result = mapper.register(insertDto) > 0 ? true : false;
		
		// 첨부파일 등록
		if(insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) {
			return false;
		}
		
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return result;
	}

	@Override
	public List<BoardDTO> getRows(Criteria cri) {
		return mapper.listAll(cri);
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

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> findByBno(int bno) {
		return boardAttachMapper.read(bno);
	}

}
