package com.sk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.BackgroundDto;

@Mapper
public interface BackgroundMapper {
	
	public void insertBackground(String id);
	public BackgroundDto getBackground(String id);
}
