package com.sk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "bookmarkDto")
public class BookmarkDto {
	
	private int bookmark_seq;
	private String id;
	private int board_seq;
	
	public int getBookmark_seq() {
		return bookmark_seq;
	}
	public void setBookmark_seq(int bookmark_seq) {
		this.bookmark_seq = bookmark_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	@Override
	public String toString() {
		return "BookmarkDto [bookmark_seq=" + bookmark_seq + ", id=" + id + ", board_seq=" + board_seq + "]";
	}
}
