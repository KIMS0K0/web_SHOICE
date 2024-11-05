package com.sk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.CommentDto;

@Mapper
public interface CommentMapper {
	public void addComment(CommentDto commentDto);
	public void deleteComment(int comment_seq);
	public List<CommentDto> getComments(int board_seq);
}
