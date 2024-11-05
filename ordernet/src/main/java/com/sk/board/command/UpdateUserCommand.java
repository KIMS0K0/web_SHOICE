package com.sk.board.command;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserCommand {

	@NotBlank(message = "이름을 입력하세요")
	private String name;

	@NotBlank(message = "이메일 입력하세요")
	private String email;
	
	@NotBlank(message = "주소를 입력하세요")
	private String address;

	public UpdateUserCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateUserCommand(@NotBlank(message = "이름을 입력하세요") String name,
			@NotBlank(message = "이메일 입력하세요") String email, @NotBlank(message = "주소를 입력하세요") String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "UpdateUserCommand [name=" + name + ", email=" + email + ", address=" + address + "]";
	}
	

}









