package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.mapper.MemberMapper;

@Service("service") // 이름을 안 붙이는 경우 MemberServiceImpl
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public List<MemberDTO> getList() {
		return mapper.list();
	}

	@Override
	public MemberDTO getRow(String userid, String password) {
		return mapper.read(userid, password);
	}

	@Override
	public boolean memberUpdate(ChangeDTO changeDto) {
		return mapper.update(changeDto) > 0 ? true : false;
	}

	@Override
	public boolean memberDelete(String userid, String password) {
		return mapper.delete(userid, password) > 0 ? true : false;
	}

	@Override
	public boolean memberInsert(MemberDTO dto) {
		return mapper.insert(dto) > 0 ? true : false;
	}



}
