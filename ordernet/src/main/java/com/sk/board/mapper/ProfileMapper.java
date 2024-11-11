package com.sk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.ProfileDto;

@Mapper
public interface ProfileMapper {
	
	//파일 정보 조회

	public void insertProfile(String id);
	public ProfileDto getProfile(String id);
	public void updateProfile(ProfileDto profileDto);
}
