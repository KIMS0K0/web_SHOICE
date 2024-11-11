package com.sk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.ReplyDto;

@Mapper
public interface ReplyMapper {

	public void addReply(ReplyDto replyDto);

	public void deleteReply(int reply_seq);
}
