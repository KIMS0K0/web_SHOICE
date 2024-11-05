package com.sk.board.dtos;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value = "memberDto")
public class MemberDto {
	
	private int memberId;
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;
	private String role;
	private String job;

	private ProfileDto profileDto;

	public MemberDto() {
		super();
	}

	public MemberDto(int memberId, String id, String name, String password, String email, String address, String role,
			String job, ProfileDto profileDto) {
		super();
		this.memberId = memberId;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.job = job;
		this.profileDto = profileDto;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public ProfileDto getProfileDto() {
		return profileDto;
	}

	public void setProfileDto(ProfileDto profileDto) {
		this.profileDto = profileDto;
	}

	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", address=" + address + ", role=" + role + ", job=" + job + ", profileDto="
				+ profileDto + "]";
	}

	
}




