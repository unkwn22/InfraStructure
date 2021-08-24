package com.example.InfraStructure.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.InfraStructure.entity.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@Email
	private String email;
	
	private String role_password;
	
	public UserRequestDto() {
		
	}
	
	public UserRequestDto(String username, String password, String email, String role_password) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role_password = role_password;
	}
}