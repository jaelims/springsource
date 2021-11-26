package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberDTO;
import com.company.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public boolean register(MemberDTO memberDto) {
		return mapper.insert(memberDto) > 0 ? true : false;
	}

	@Override
	public MemberDTO dupId(String userid) {
		return mapper.selectById(userid);
	}

}
