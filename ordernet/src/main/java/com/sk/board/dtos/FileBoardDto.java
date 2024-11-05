package com.sk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "fileBoardDto")
public class FileBoardDto {

	private int file_seq;
	private int board_seq;
	private String origin_name;
	private String stored_name;
	
	public FileBoardDto() {
		super();
	}

	public FileBoardDto(int file_seq, int board_seq, String origin_name, String stored_name) {
		super();
		this.file_seq = file_seq;
		this.board_seq = board_seq;
		this.origin_name = origin_name;
		this.stored_name = stored_name;
	}

	public int getFile_seq() {
		return file_seq;
	}

	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getStored_name() {
		return stored_name;
	}

	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}

	@Override
	public String toString() {
		return "FileBoardDto [file_seq=" + file_seq + ", board_seq=" + board_seq + ", origin_name="
				+ origin_name + ", stored_name=" + stored_name + "]";
	}
	
}
