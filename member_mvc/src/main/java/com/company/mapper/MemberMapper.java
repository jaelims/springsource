package com.company.mapper;

import com.company.domain.MemberDTO;

public interface MemberMapper {
	public int insert(MemberDTO memberDto);
	public MemberDTO selectById(String userid);
}
