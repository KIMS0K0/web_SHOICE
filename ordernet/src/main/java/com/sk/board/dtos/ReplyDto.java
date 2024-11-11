package com.sk.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "replyDto")
public class ReplyDto {
	private int reply_seq;
	private int comment_seq;
	private String id;
	private String content;
	private Date regdate;
	
	private MemberDto member;
	
	public ReplyDto() {
		super();
	}

	public ReplyDto(int reply_seq, int comment_seq, String id, String content, Date regdate, MemberDto member) {
		super();
		this.reply_seq = reply_seq;
		this.comment_seq = comment_seq;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.member = member;
	}

	public int getReply_seq() {
		return reply_seq;
	}

	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}

	public int getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
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
		return "ReplyDto [reply_seq=" + reply_seq + ", comment_seq=" + comment_seq + ", id=" + id + ", content="
				+ content + ", regdate=" + regdate + ", member=" + member + "]";
	}
	
}
