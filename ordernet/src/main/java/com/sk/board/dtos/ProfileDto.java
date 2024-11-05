package com.sk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "profileDto")
public class ProfileDto {

	private int file_seq;
	private String id;
	private String origin_name;
	private String stored_name;
	
	public ProfileDto() {
		super();
	}

	public int getFile_seq() {
		return file_seq;
	}

	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ProfileDto(int file_seq, String id, String origin_name, String stored_name) {
		super();
		this.file_seq = file_seq;
		this.id = id;
		this.origin_name = origin_name;
		this.stored_name = stored_name;
	}

	@Override
	public String toString() {
		return "ProfileDto [file_seq=" + file_seq + ", id=" + id + ", origin_name=" + origin_name + ", stored_name="
				+ stored_name + "]";
	}

}
