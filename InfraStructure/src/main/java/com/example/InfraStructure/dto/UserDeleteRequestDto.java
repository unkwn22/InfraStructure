package com.example.InfraStructure.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteRequestDto {
	
	@NotNull
	private String username;
	@NotNull
	private String password;
	
	public UserDeleteRequestDto() {
		
	}
	
	public UserDeleteRequestDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
