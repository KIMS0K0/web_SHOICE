package com.sk.board.dtos;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value = "commentDto")
public class CommentDto {
	private int comment_seq;
	private int board_seq;
	private String id;
	private String content;
	private Date regdate;
	
	private MemberDto member;
	private List<ReplyDto> replies; 
	
	public CommentDto() {
		super();
	}

	public CommentDto(int comment_seq, int board_seq, String id, String content, Date regdate, MemberDto member,
			List<ReplyDto> replies) {
		super();
		this.comment_seq = comment_seq;
		this.board_seq = board_seq;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.member = member;
		this.replies = replies;
	}

	public int getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public MemberDto getMember() {
		return member;
	}

	public void setMember(MemberDto member) {
		this.member = member;
	}

	public List<ReplyDto> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyDto> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "CommentDto [comment_seq=" + comment_seq + ", board_seq=" + board_seq + ", id=" + id + ", content="
				+ content + ", regdate=" + regdate + ", member=" + member + ", replies=" + replies + "]";
	}
}
