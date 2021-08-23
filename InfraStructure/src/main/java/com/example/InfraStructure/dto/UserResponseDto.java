package com.example.InfraStructure.dto;

import com.example.InfraStructure.entity.Users;

import lombok.Getter;

@Getter
public class UserResponseDto {

	private String username;
	
	private String password;
	
	private String email;
	
	private String roles;
	
	public UserResponseDto(Users user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.roles = user.getRoles();
	}
		
		
}
