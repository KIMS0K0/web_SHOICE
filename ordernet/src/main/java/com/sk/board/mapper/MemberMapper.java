package com.sk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.MemberDto;

@Mapper
public interface MemberMapper {
	
	public boolean addUser(MemberDto dto);
	
	public String idChk(String id);
	
	public MemberDto loginUser(String id);
	
	public boolean updateUser(MemberDto dto);

	public MemberDto getUser(int memberid);

	public MemberDto getUser(String yourid);

	public MemberDto getMemberId(String id);

}







