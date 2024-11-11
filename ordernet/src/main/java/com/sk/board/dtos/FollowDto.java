package com.sk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "followDto")
public class FollowDto {
	private int follow_seq;
	private String id;
	private String following_id;
	
	public FollowDto() {
		super();
	}

	public FollowDto(int follow_seq, String id, String following_id) {
		super();
		this.follow_seq = follow_seq;
		this.id = id;
		this.following_id = following_id;
	}

	public int getFollow_seq() {
		return follow_seq;
	}

	public void setFollow_seq(int follow_seq) {
		this.follow_seq = follow_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFollowing_id() {
		return following_id;
	}

	public void setFollowing_id(String following_id) {
		this.following_id = following_id;
	}

	@Override
	public String toString() {
		return "FollowDto [follow_seq=" + follow_seq + ", id=" + id + ", following_id=" + following_id + "]";
	}
	
}
