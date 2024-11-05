package com.sk.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "commentDto")
public class CommentDto {
	private int comment_seq;
	private int board_seq;
	private String id;
	private String content;
	private Date regdate;
	
	private MemberDto member;
	
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(int comment_seq, int board_seq, String id, String content, Date regdate, MemberDto member) {
		super();
		this.comment_seq = comment_seq;
		this.board_seq = board_seq;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.member = member;
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

	@Override
	public String toString() {
		return "CommentDto [comment_seq=" + comment_seq + ", board_seq=" + board_seq + ", id=" + id + ", content="
				+ content + ", regdate=" + regdate + ", member=" + member + "]";
	}

}
