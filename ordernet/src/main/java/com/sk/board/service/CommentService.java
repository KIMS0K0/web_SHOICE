package com.sk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.board.dtos.CommentDto;
import com.sk.board.mapper.CommentMapper;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment(CommentDto commentDto) {
		commentMapper.addComment(commentDto);
	}
	
	public void deleteComment(int comment_seq) {
		commentMapper.deleteComment(comment_seq);
	}
	
	public List<CommentDto> getComments(int board_seq) {
		return commentMapper.getComments(board_seq);
	}

	public void addReply(CommentDto commentDto) {
		commentMapper.addReply(commentDto);
	}
}
