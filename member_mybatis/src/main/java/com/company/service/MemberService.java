package com.company.service;

import java.util.List;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public List<MemberDTO> getList();
	public MemberDTO getRow(String userid, String password);
	public boolean memberUpdate(ChangeDTO changeDto);
	public boolean memberDelete(String userid, String password);
	public boolean memberInsert(MemberDTO dto);
}
