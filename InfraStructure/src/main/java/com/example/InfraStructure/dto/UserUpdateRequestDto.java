package com.example.InfraStructure.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

	@NotNull
	private String username;
	@NotNull
	private String previous_pass;
	@NotNull
	private String change_pass;
	@Email
	private String change_email;
	
	
}
