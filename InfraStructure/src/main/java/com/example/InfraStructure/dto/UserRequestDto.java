package com.example.InfraStructure.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
}
