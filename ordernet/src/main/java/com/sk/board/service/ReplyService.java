package com.sk.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.board.dtos.ReplyDto;
import com.sk.board.mapper.ReplyMapper;

@Service
public class ReplyService {
	@Autowired
	private ReplyMapper replyMapper;

	public void addReply(ReplyDto replyDto) {
		replyMapper.addReply(replyDto);
	}

	public void deleteReply(int reply_seq) {
		replyMapper.deleteReply(reply_seq);
	}
}
