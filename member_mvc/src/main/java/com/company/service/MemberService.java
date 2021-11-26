package com.company.service;

import com.company.domain.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO memberDto);
	public MemberDTO dupId(String userid);
}
