package com.sk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "followDto")
public class FollowDto {

	private int follow_seq;
	private String id;
	private String following_id;
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
